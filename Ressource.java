public class Ressource {
    private final String idRess;
    private final String nomRess;
    private boolean disponible;
    public Ressource(String idRess, String nomRess) {
        if (idRess == null || idRess.equals("")) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être nul ou vide");
        }
        if (nomRess == null || nomRess.equals("")) {
            throw new IllegalArgumentException("Le nom ne doit pas être nul ou vide");
        }
        this.idRess = idRess;
        this.nomRess = nomRess;
        this.disponible = true;
    }
    public String getIdRess() {
        return idRess;
    }
    public String getNomRess() {
        return nomRess;
    }
    public boolean estDisponible() {
        return disponible;
    }
    public void afficher() {
        System.out.println(nomRess+"numero"+idRess+"disponible:"+disponible);
    }
}
