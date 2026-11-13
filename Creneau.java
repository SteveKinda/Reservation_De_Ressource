import java.util.Objects;

public class Creneau{
    private final String jour;
    private final int heureDebut;
    private final int heureFin;
    public Creneau(String jour, int heureDebut, int heureFin){
        if (jour == null || jour. isBlank() ){
            throw new IllegalArgumentException("Jour nul ou vide");
        }
        if (heureDebut<0){
            throw new IllegalArgumentException("L'heure de début ne doit pas etre negative");
        }
        if (heureFin<0){
            throw new IllegalArgumentException("L'heure de fin ne doit pas etre negative");
        }
        if (heureFin<=heureDebut){
            throw new IllegalArgumentException("L'heure de fin doit être supérieur à l'heure de début");
        }
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }
    public String getJour(){
        return jour;
    }
    public int getHeureDebut(){
        return heureDebut;
    }
    public int getHeureFin(){
        return heureFin;
    }
    public int getDuree(){
        return heureFin - heureDebut;
    }
    public boolean chevauche(Creneau autre){
    if (!jour.equals(autre.jour)){
        return false;          
    }
    if (heureFin <= autre.heureDebut){
        return false;            
    }
    if (autre.heureFin <= heureDebut){
        return false;           
    }
    return true;
    }                
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Creneau)) return false;
        Creneau autre = (Creneau) o;
        return heureDebut == autre.heureDebut && heureFin == autre.heureFin && jour.equals(autre.jour);
    }
    @Override
    public int hashCode(){
        return Objects.hash(jour, heureDebut, heureFin);
    }
    @Override
    public String toString(){
        return jour + " de " + heureDebut + " h à " + heureFin + " h";
    }
    public void afficher(){
        System.out.println("Créneau : " + jour + " de " + heureDebut + " h à " + heureFin + " h");
    }
}
