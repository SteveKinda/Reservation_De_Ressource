public class Utilisateur {
    private final String id;
    private final String nom;
    private final String email;

    public Utilisateur(String id, String nom, String email){
        if (id == null || id.equals("")){
            throw new IllegalArgumentException("Identifiant nul ou vide");
        }
        if (nom == null || nom.equals("")){
            throw new IllegalArgumentException("Nom nul ou vide");
        }
        if (email == null || email.equals("")){
            throw new IllegalArgumentException("Adresse électronique nulle ou vide");
        }
        this.id = id;
        this.nom = nom;
        this.email = email;
    }
    public String getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public String getEmail(){
        return email;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        return id.equals(((Utilisateur) o).id);
    }
    @Override
    public int hashCode(){
        return id.hashCode();
    }
    @Override
    public String toString(){
        return nom + " (" + id + ")";
    }
    public void afficher(){
        System.out.println("Utilisateur " + id + " : " + nom + " (" + email + ")");
    }
}
