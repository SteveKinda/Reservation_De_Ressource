import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServiceReservation {
    private final CalendrierReservations calendrier;
    private final Map<String, Ressource> ressources = new LinkedHashMap<>();

    public ServiceReservation(CalendrierReservations calendrier) {
        if (calendrier == null) {
            throw new IllegalArgumentException("Le calendrier ne doit pas être nul");
        }
        this.calendrier = calendrier;
    }
     
    public void ajouterRessource(Ressource ressource) {
        if (ressource == null) {
            throw new DonneeInvalideException("La ressource ne doit pas être nulle");
        }
        if (ressources.containsKey(ressource.getIdRess())) {
            throw new DonneeInvalideException("Identifiant de ressource déjà utilisé : " + ressource.getIdRess());
        }
        ressources.put(ressource.getIdRess(), ressource);
    }

    public Reservation reserver(Utilisateur utilisateur, String idRessource, Creneau creneau) {
        if (utilisateur == null) {
            throw new DonneeInvalideException("L'utilisateur ne doit pas être nul");
        }
        if (idRessource == null || idRessource.isBlank()) {
            throw new DonneeInvalideException("L'identifiant de la ressource ne doit pas être nul ou vide");
        }
        if (creneau == null) {
            throw new DonneeInvalideException("Le créneau ne doit pas être nul");
        }
        Ressource ressource = ressources.get(idRessource);
        if (ressource == null) {
            throw new RessourceAbsenteException("Ressource inconnue : " + idRessource);
        }
        if (!ressource.estCompatible(creneau)) {
            throw new DonneeInvalideException(ressource + " n'est pas compatible avec le créneau " + creneau);
        }
        Reservation reservation = new Reservation(utilisateur, ressource, creneau);
        if (!calendrier.ajouter(reservation)) {
            throw new ConflitCreneauException(ressource + " est déjà réservée sur un créneau qui chevauche " + creneau);
        }
        return reservation;
    }

    public void annuler(Reservation reservation) {
        if (reservation == null) {
            throw new DonneeInvalideException("La réservation ne doit pas être nulle");
        }
        String id = reservation.getRessource().getIdRess();
        if (!ressources.containsKey(id)) {
            throw new RessourceAbsenteException("Ressource inconnue : " + id);
        }
        if (!calendrier.annuler(reservation)) {
            throw new ReservationIntrouvableException("Réservation introuvable : " + reservation);
        }
    }

    public List<Reservation> reservationsDe(String idRessource) {
        Ressource ressource = idRessource == null ? null : ressources.get(idRessource);
        if (ressource == null) {
            throw new RessourceAbsenteException("Ressource inconnue : " + idRessource);
        }
        return calendrier.rechercherParRessource(ressource);
    }
}
