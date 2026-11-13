import java.util.List;

public interface CalendrierReservations {
    boolean ajouter(Reservation reservation);
    boolean annuler(Reservation reservation);
    boolean estLibre(Ressource ressource, Creneau creneau);
    List<Reservation> rechercherParRessource(Ressource ressource);
    List<Reservation> rechercherParPeriode(Creneau periode);
    int taille();
}
