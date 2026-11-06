public class Equipement extends Ressource{
    private final String categorie;
    private final int dureeMaximale;

    public Equipement(String id, String nom, String localisation,String categorie, int dureeMaximale){
        super(id, nom, localisation);
        if (categorie == null || categorie.isBlank()){
            throw new IllegalArgumentException("La catégorie ne doit pas être nulle ou vide");
        }
        if (dureeMaximale <= 0){
            throw new IllegalArgumentException("La durée maximale doit être positive");
        }
        this.categorie = categorie;
        this.dureeMaximale = dureeMaximale;
    }

    public String getCategorie(){ 
        return categorie; 
    }

    @Override
    public boolean estCompatible(Creneau creneau){
        return creneau != null && creneau.getDuree() <= dureeMaximale;
    }
}
