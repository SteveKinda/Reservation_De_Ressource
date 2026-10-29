public class Creneau{
  private final String jour;
  private final int heureDebut;
  private final int heureFin;
  public Creneau(String jour,int heureDebut,int heureFin){
    if (jour==null||jour.isBlank()){
      throw new IllegalArgumentException("Jour nul ou vide");
    }
    if (heureDebut<0){
      throw new IllegalArgumentException("L'heure de début ne doit pas etre negative");
    }
    if(heureFin<0){
      throw new IllegalArgumentException("L'heure de fin ne doit pas etre negative");
    }
    if(heureFin<=heureDebut){
      throw new IllegalArgumentException("L'heure de fin doit etre superieur à l'heure de début");
    }

    this.jour=jour;
    this.heureDebut=heureDebut;
    this.heureFin=heureFin;
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
  public void afficher(){
    System.out.println("Créneau:"+jour+"de"+heureDebut+"h à"+heureFin+"h");
  }
}

    
      
