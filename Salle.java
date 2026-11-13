public class Salle extends Ressource{
    private final int capacite;
    private final int heureOuverture;
    private final int heureFermeture;

    public Salle(String id, String nom, String localisation, int capacite,int heureOuverture, int heureFermeture){
        super(id, nom, localisation);
        if (capacite <= 0) throw new IllegalArgumentException("La capacité doit être positive");
        if (heureOuverture < 0 || heureFermeture > 24 || heureFermeture <= heureOuverture){
            throw new IllegalArgumentException("Les horaires d'ouverture sont invalides");
        }
        this.capacite = capacite;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
    }
    public int getCapacite(){ 
        return capacite; 
    }
    @Override
    public boolean estCompatible(Creneau creneau){
        return creneau != null && creneau.getHeureDebut() >= heureOuverture && creneau.getHeureFin() <= heureFermeture;
    }
}
