import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalendrierMemoire implements CalendrierReservations {
    private final Map<Ressource, List<Reservation>> reservationsParRessource = new LinkedHashMap<>();

    @Override
    public boolean ajouter(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("La réservation ne doit pas être nulle");
        }
        Ressource ressource = reservation.getRessource();
        if (!estLibre(ressource, reservation.getCreneau())) {
            return false;
        }
        reservationsParRessource.computeIfAbsent(ressource, r -> new ArrayList<>()).add(reservation);
        return true;
    }

    @Override
    public boolean annuler(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("La réservation ne doit pas être nulle");
        }
        List<Reservation> liste = reservationsParRessource.get(reservation.getRessource());
        if (liste == null || !liste.remove(reservation)) {
            return false;
        }
        if (liste.isEmpty()) {
            reservationsParRessource.remove(reservation.getRessource());
        }
        return true;
    }

    @Override
    public boolean estLibre(Ressource ressource, Creneau creneau) {
        if (ressource == null) {
            throw new IllegalArgumentException("La ressource ne doit pas être nulle");
        }
        if (creneau == null) {
            throw new IllegalArgumentException("Le créneau ne doit pas être nul");
        }
        List<Reservation> liste = reservationsParRessource.get(ressource);
        if (liste == null) {
            return true;
        }
        for (Reservation existante : liste) {
            if (existante.getCreneau().chevauche(creneau)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Reservation> rechercherParRessource(Ressource ressource) {
        if (ressource == null) {
            throw new IllegalArgumentException("La ressource ne doit pas être nulle");
        }
        List<Reservation> liste = reservationsParRessource.get(ressource);
        return liste == null ? List.of() : List.copyOf(liste);
    }

    @Override
    public List<Reservation> rechercherParPeriode(Creneau periode) {
        if (periode == null) {
            throw new IllegalArgumentException("La période ne doit pas être nulle");
        }
        List<Reservation> resultat = new ArrayList<>();
        for (List<Reservation> liste : reservationsParRessource.values()) {
            for (Reservation r : liste) {
                if (r.getCreneau().chevauche(periode)) {
                    resultat.add(r);
                }
            }
        }
        return List.copyOf(resultat);
    }

    @Override
    public int taille() {
        int total = 0;
        for (List<Reservation> liste : reservationsParRessource.values()) {
            total += liste.size();
        }
        return total;
    }
}
