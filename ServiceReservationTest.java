import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceReservationTest {
    private CalendrierMemoire calendrier;
    private ServiceReservation service;
    private Utilisateur utilisateur;
    private Salle salle;
    private Equipement projecteur;
    private Creneau matin;

    @BeforeEach
    void preparer() {
        calendrier = new CalendrierMemoire();
        service = new ServiceReservation(calendrier);
        utilisateur = new Utilisateur("U1", "Steve", "steve@email.com");
        salle = new Salle("S1", "Salle 101", "S001", 30, 8, 18);
        projecteur = new Equipement("E1", "Projecteur", "E001", "video", 4);
        matin = new Creneau("Lundi", 8, 10);
        service.ajouterRessource(salle);
        service.ajouterRessource(projecteur);
    }

    @Test
    void reserverEnregistreLaReservation() {
        Reservation r = service.reserver(utilisateur, "S1", matin);
        assertEquals(salle, r.getRessource());
        assertEquals(1, calendrier.taille());
        assertEquals(1, service.reservationsDe("S1").size());
    }

    @Test
    void reserverJusquALaFermetureDeLaSalleEstAccepte() {
        service.reserver(utilisateur, "S1", new Creneau("Lundi", 16, 18));
        assertEquals(1, calendrier.taille());
    }

    @Test
    void reserverUnEquipementPourSaDureeMaximaleEstAccepte() {
        service.reserver(utilisateur, "E1", new Creneau("Lundi", 8, 12));
        assertEquals(1, calendrier.taille());
    }

    @Test
    void conflitDeCreneauxLanceUneExceptionEtLaisseLeCalendrierInchange() {
        service.reserver(utilisateur, "S1", matin);
        assertThrows(ConflitCreneauException.class,
                () -> service.reserver(utilisateur, "S1", new Creneau("Lundi", 9, 11)));
        assertEquals(1, calendrier.taille());
    }

    @Test
    void ressourceAbsenteLanceUneExceptionEtLaisseLeCalendrierInchange() {
        assertThrows(RessourceAbsenteException.class, () -> service.reserver(utilisateur, "S999", matin));
        assertEquals(0, calendrier.taille());
    }

    @Test
    void salleHorsDeSesHorairesEstRefuseeEtLeCalendrierResteInchange() {
        assertThrows(DonneeInvalideException.class,
                () -> service.reserver(utilisateur, "S1", new Creneau("Lundi", 6, 8)));
        assertThrows(DonneeInvalideException.class,
                () -> service.reserver(utilisateur, "S1", new Creneau("Lundi", 17, 19)));
        assertEquals(0, calendrier.taille());
    }

    @Test
    void equipementAuDelaDeSaDureeMaximaleEstRefuseEtLeCalendrierResteInchange() {
        assertThrows(DonneeInvalideException.class,
                () -> service.reserver(utilisateur, "E1", new Creneau("Lundi", 8, 13)));
        assertEquals(0, calendrier.taille());
    }

    @Test
    void argumentsNulsOuVidesSontRefuses() {
        assertThrows(DonneeInvalideException.class, () -> service.reserver(null, "S1", matin));
        assertThrows(DonneeInvalideException.class, () -> service.reserver(utilisateur, null, matin));
        assertThrows(DonneeInvalideException.class, () -> service.reserver(utilisateur, " ", matin));
        assertThrows(DonneeInvalideException.class, () -> service.reserver(utilisateur, "S1", null));
        assertEquals(0, calendrier.taille());
    }


    @Test
    void annulerLibereLaRessource() {
        Reservation r = service.reserver(utilisateur, "S1", matin);
        service.annuler(r);
        assertEquals(0, calendrier.taille());
        service.reserver(utilisateur, "S1", matin);   // le même créneau est de nouveau disponible
        assertEquals(1, calendrier.taille());
    }

    @Test
    void annulerUneReservationInexistanteLanceUneExceptionEtLaisseLeCalendrierInchange() {
        service.reserver(utilisateur, "S1", matin);
        Reservation inconnue = new Reservation(utilisateur, salle, new Creneau("Mardi", 8, 10));
        assertThrows(ReservationIntrouvableException.class, () -> service.annuler(inconnue));
        assertEquals(1, calendrier.taille());
    }

    @Test
    void annulerUneReservationNulleOuDUneRessourceInconnueEstRefuse() {
        assertThrows(DonneeInvalideException.class, () -> service.annuler(null));
        Salle inconnue = new Salle("S9", "Salle 909", "S009", 10, 8, 18);
        Reservation r = new Reservation(utilisateur, inconnue, matin);
        assertThrows(RessourceAbsenteException.class, () -> service.annuler(r));
    }


    @Test
    void identifiantDeRessourceDejaUtiliseEstRefuse() {
        Salle doublon = new Salle("S1", "Autre salle", "S002", 10, 8, 18);
        assertThrows(DonneeInvalideException.class, () -> service.ajouterRessource(doublon));
        assertThrows(DonneeInvalideException.class, () -> service.ajouterRessource(null));
    }

    @Test
    void reservationsDUneRessourceInconnueLanceUneException() {
        assertThrows(RessourceAbsenteException.class, () -> service.reservationsDe("S999"));
    }
}
