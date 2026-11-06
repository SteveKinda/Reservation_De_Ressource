public abstract class Ressource{
    private final String idRess;
    private final String nomRess;
    private final String localisation;

    protected Ressource(String idRess, String nomRess, String localisation){
        if (idRess == null || idRess.isBlank()){
            throw new IllegalArgumentException("L'identifiant ne doit pas être nul ou vide");
        }
        if (nomRess == null || nomRess.isBlank()){
            throw new IllegalArgumentException("Le nom ne doit pas être nul ou vide");
        }
        if (localisation == null || localisation.isBlank()){
            throw new IllegalArgumentException("La localisation ne doit pas être nulle ou vide");
        }
        this.idRess = idRess;
        this.nomRess = nomRess;
        this.localisation = localisation;
    }

    public String getIdRess(){ 
        return idRess; 
    }
    public String getNomRess(){ 
        return nomRess; 
    }
    public String getLocalisation(){ 
        return localisation; 
    }

    public abstract boolean estCompatible(Creneau creneau);

    public void afficher(){
        System.out.println(nomRess + " (" + idRess + ")  " + localisation);
    }
}
