/**
 * Démonstration en ligne de commande (recette) : elle montre un scénario complet.
 * Les vérifications automatiques sont dans les tests JUnit.
 */
public class Main{
    public static void main(String[] args){
        CalendrierReservations calendrier = new CalendrierMemoire();
        ServiceReservation service = new ServiceReservation(calendrier);

        Utilisateur steve = new Utilisateur("U1", "Kinda Steve", "steve@email.com");
        service.ajouterRessource(new Salle("S1", "Salle 101", "Bâtiment A", 30, 8, 18));
        service.ajouterRessource(new Equipement("E1", "Projecteur", "Bâtiment A", "video", 4));

        System.out.println("1. Réservation valide");
        Reservation r1 = service.reserver(steve, "S1", new Creneau("Lundi", 8, 10));
        System.out.println("   OK : " + r1);

        tenter(service, steve, "2. Conflit de créneaux", "S1", new Creneau("Lundi", 9, 11));
        tenter(service, steve, "3. Créneau adjacent (accepté)", "S1", new Creneau("Lundi", 10, 12));
        tenter(service, steve, "4. Ressource absente", "S999", new Creneau("Lundi", 8, 10));
        tenter(service, steve, "5. Salle hors de ses horaires", "S1", new Creneau("Lundi", 6, 8));
        tenter(service, steve, "6. Équipement trop longtemps", "E1", new Creneau("Mardi", 8, 13));

        System.out.println("7. Annulation de la première réservation");
        service.annuler(r1);
        System.out.println("   Réservations restantes de S1 : " + service.reservationsDe("S1"));
    }

    private static void tenter(ServiceReservation service, Utilisateur u, String titre, String idRessource, Creneau creneau) {
        System.out.println(titre);
        try{
            Reservation r = service.reserver(u, idRessource, creneau);
            System.out.println("   OK : " + r);
        } catch (ReservationException e){
            System.out.println("   Refusé (" + e.getClass().getSimpleName() + ") : " + e.getMessage());
        }
    }
}
