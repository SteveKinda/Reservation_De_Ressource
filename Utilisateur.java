public class Utilisateur {
    private final String id;
    private final String nom;
    private final String email;

    public Utilisateur(String id, String nom, String email) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("Identifiant nul ou vide");
        }
        if (nom == null || nom.equals("")) {
            throw new IllegalArgumentException("Nom nul ou vide");
        }
        if (email == null || email.equals("")) {
            throw new IllegalArgumentException("Adresse électronique nulle ou vide");
        }

        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void afficher() {
        System.out.println("Utilisateur " + id + " : " + nom + " (" + email + ")");
    }
}

