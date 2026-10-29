public class Main {
    public static void main(String[] args) {
        Utilisateur utilisateur01 = new Utilisateur("U1", "Kinda Steve", "steve@email.com");
        utilisateur01.afficher();
        
        Ressource ressource01 = new Ressource("R1", "Salle 101");
        ressource01.afficher();
        
        Creneau c1 = new Creneau("Lundi", 8, 10);
        c1.afficher();
        
        try {
            Utilisateur utilisateurInvalide = new Utilisateur("", "Anicet", "Anicet@email.com");
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage() + "\n");
        }
        

        try {
            Ressource ressInvalide = new Ressource("R2", null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
       
        try {
            Creneau cInvalide = new Creneau("Mardi", 14, 12);
        } 
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}