import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Service de réservation : il applique les règles métier et délègue le stockage
 * à un CalendrierReservations.
 *
 * Le service ne connaît que l'interface CalendrierReservations (jamais
 * CalendrierMemoire) : le calendrier lui est fourni à la construction, ce qui permet
 * de changer de stockage sans modifier le service.
 *
 * Atomicité : toutes les vérifications ont lieu AVANT de modifier le calendrier.
 * Le calendrier n'est touché que par le tout dernier appel, et seulement s'il réussit.
 */
public class ServiceReservation {
    private final CalendrierReservations calendrier;
    private final Map<String, Ressource> ressources = new LinkedHashMap<>();

    public ServiceReservation(CalendrierReservations calendrier) {
        if (calendrier == null) {
            throw new IllegalArgumentException("Le calendrier ne doit pas être nul");
        }
        this.calendrier = calendrier;
    }

    /**
     * Déclare une ressource réservable.
     * @throws DonneeInvalideException si la ressource est nulle ou si son identifiant est déjà utilisé
     */
    public void ajouterRessource(Ressource ressource) {
        if (ressource == null) {
            throw new DonneeInvalideException("La ressource ne doit pas être nulle");
        }
        if (ressources.containsKey(ressource.getIdRess())) {
            throw new DonneeInvalideException("Identifiant de ressource déjà utilisé : " + ressource.getIdRess());
        }
        ressources.put(ressource.getIdRess(), ressource);
    }

    /**
     * Réserve une ressource sur un créneau.
     * @throws DonneeInvalideException si un argument est nul/vide ou si la ressource
     *         n'est pas compatible avec le créneau (horaires de salle, durée maximale d'équipement)
     * @throws RessourceAbsenteException si l'identifiant ne correspond à aucune ressource
     * @throws ConflitCreneauException si la ressource est déjà réservée sur une plage qui se recoupe
     * Dans tous les cas d'échec, le calendrier reste inchangé.
     */
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

    /**
     * Annule une réservation.
     * @throws DonneeInvalideException si la réservation est nulle
     * @throws RessourceAbsenteException si sa ressource n'est pas connue du service
     * @throws ReservationIntrouvableException si elle n'existe pas dans le calendrier
     * Dans tous les cas d'échec, le calendrier reste inchangé.
     */
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

    /**
     * Réservations d'une ressource.
     * @throws RessourceAbsenteException si l'identifiant ne correspond à aucune ressource
     */
    public List<Reservation> reservationsDe(String idRessource) {
        Ressource ressource = idRessource == null ? null : ressources.get(idRessource);
        if (ressource == null) {
            throw new RessourceAbsenteException("Ressource inconnue : " + idRessource);
        }
        return calendrier.rechercherParRessource(ressource);
    }
}
