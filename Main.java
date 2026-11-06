public class Main {
    private static int nombreEssais = 0;

    private static void verifier(boolean resultat, String nom) {
        nombreEssais++;
        if (!resultat) {
            throw new AssertionError("Échec : " + nom);
        }
        System.out.println("Essai réussi : " + nom);
    }

    public static void main(String[] args) {
        Localisation localisation = new Localisation("Bâtiment A", 1);

        Utilisateur utilisateur = new Utilisateur("U1", "Kinda Steve", "steve@email.com");
        verifier(utilisateur.getId().equals("U1"), "création d'un utilisateur");
        try {
            new Utilisateur("", "Anicet", "a@email.com");
            throw new AssertionError("Un identifiant vide devait être refusé");
        } catch (IllegalArgumentException e) {
            verifier(true, "identifiant vide refusé");
        }

        Creneau matin = new Creneau("Lundi", 8, 10);
        Creneau tropLong = new Creneau("Lundi", 8, 14);
        verifier(matin.getDuree() == 2, "durée du créneau calculée");
        try {
            new Creneau("Mardi", 14, 12);
            throw new AssertionError("Un créneau invalide devait être refusé");
        } catch (IllegalArgumentException e) {
            verifier(true, "créneau invalide refusé");
        }

        Salle salle = new Salle("S1", "Salle 101","S001", 30, 8, 18);
        Equipement projecteur = new Equipement("E1", "Projecteur","E001", "video", 4);
        verifier(salle.getCapacite() == 30, "capacite de la salle");

        Ressource ressourceSalle = salle;
        Ressource ressourceEquipement = projecteur;
        verifier(ressourceSalle.estCompatible(matin), "salle compatible avec le creneau");
        verifier(ressourceEquipement.estCompatible(matin), "équipement compatible avec le creneau");
        verifier(!ressourceEquipement.estCompatible(tropLong), "équipement refuse un créneau trop long");

        System.out.println(nombreEssais + " essais réussis.");
    }
}
