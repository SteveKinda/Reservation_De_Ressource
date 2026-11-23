import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CalendrierMemoireTest{
    private CalendrierReservations calendrier;
    private Utilisateur utilisateur;
    private Salle salle;
    private Equipement projecteur;
    private Creneau matin;

    @BeforeEach
    void preparer(){
        calendrier = new CalendrierMemoire();
        utilisateur = new Utilisateur("U1", "Steve", "steve@email.com");
        salle = new Salle("S1", "Salle 101", "S001", 30, 8, 18);
        projecteur = new Equipement("E1", "Projecteur", "E001", "video", 4);
        matin = new Creneau("Lundi", 8, 10);
    }

    @Test
    void ajoutPuisRechercheParRessource(){
        Reservation r = new Reservation(utilisateur, salle, matin);
        assertTrue(calendrier.ajouter(r));
        assertEquals(1, calendrier.taille());
        assertEquals(List.of(r), calendrier.rechercherParRessource(salle));
    }

    @Test
    void conflitEstRefuseEtLeCalendrierResteInchange(){
        calendrier.ajouter(new Reservation(utilisateur, salle, matin));
        Reservation conflit = new Reservation(utilisateur, salle, new Creneau("Lundi", 9, 11));
        assertFalse(calendrier.ajouter(conflit));
        assertEquals(1, calendrier.taille());
    }

    @Test
    void creneauxAdjacentsSontAcceptes(){
        calendrier.ajouter(new Reservation(utilisateur, salle, matin));
        assertTrue(calendrier.ajouter(new Reservation(utilisateur, salle, new Creneau("Lundi", 10, 12))));
        assertEquals(2, calendrier.taille());
    }

    @Test
    void memeCreneauSurUneAutreRessourceEstAccepte(){
        calendrier.ajouter(new Reservation(utilisateur, salle, matin));
        assertTrue(calendrier.ajouter(new Reservation(utilisateur, projecteur, matin)));
        assertEquals(2, calendrier.taille());
    }

    @Test
    void rechercheParPeriodeTrouveLesReservationsQuiSeRecoupent(){
        calendrier.ajouter(new Reservation(utilisateur, salle, matin));
        calendrier.ajouter(new Reservation(utilisateur, projecteur, matin));
        calendrier.ajouter(new Reservation(utilisateur, salle, new Creneau("Lundi", 14, 16)));
        assertEquals(2, calendrier.rechercherParPeriode(new Creneau("Lundi", 9, 11)).size());
        assertTrue(calendrier.rechercherParPeriode(new Creneau("Vendredi", 8, 10)).isEmpty());
    }

    @Test
    void annulationPuisAnnulationDUneReservationAbsente(){
        Reservation r = new Reservation(utilisateur, salle, matin);
        calendrier.ajouter(r);
        assertTrue(calendrier.annuler(r));
        assertFalse(calendrier.annuler(r));
        assertTrue(calendrier.estLibre(salle, matin));
        assertEquals(0, calendrier.taille());
    }

    @Test
    void listeRetourneeEstProtegee(){
        calendrier.ajouter(new Reservation(utilisateur, salle, matin));
        List<Reservation> liste = calendrier.rechercherParRessource(salle);
        assertThrows(UnsupportedOperationException.class, liste::clear);
        assertEquals(1, calendrier.taille());
    }

    @Test
    void argumentsAbsentsSontRefuses(){
        assertThrows(IllegalArgumentException.class, () -> calendrier.ajouter(null));
        assertThrows(IllegalArgumentException.class, () -> calendrier.annuler(null));
        assertThrows(IllegalArgumentException.class, () -> calendrier.estLibre(null, matin));
        assertThrows(IllegalArgumentException.class, () -> calendrier.estLibre(salle, null));
        assertThrows(IllegalArgumentException.class, () -> calendrier.rechercherParRessource(null));
        assertThrows(IllegalArgumentException.class, () -> calendrier.rechercherParPeriode(null));
        assertEquals(0, calendrier.taille());
    }
}
