import java.util.Objects;

public final class Reservation {
    private final Utilisateur utilisateur;
    private final Ressource ressource;
    private final Creneau creneau;

    public Reservation(Utilisateur utilisateur, Ressource ressource, Creneau creneau) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("L'utilisateur ne doit pas être nul");
        }
        if (ressource == null) {
            throw new IllegalArgumentException("La ressource ne doit pas être nulle");
        }
        if (creneau == null) {
            throw new IllegalArgumentException("Le créneau ne doit pas être nul");
        }
        this.utilisateur = utilisateur;
        this.ressource = ressource;
        this.creneau = creneau;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public Ressource getRessource() {
        return ressource;
    }
    public Creneau getCreneau() {
        return creneau;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation autre = (Reservation) o;
        return utilisateur.equals(autre.utilisateur)&& ressource.equals(autre.ressource)&& creneau.equals(autre.creneau);
    }
    @Override
    public int hashCode(){
        return Objects.hash(utilisateur, ressource, creneau);
    }
    @Override
    public String toString(){
        return ressource + " réservé par " + utilisateur + " : " + creneau;
    }
}
