/** Ressource absente : l'identifiant ne correspond à aucune ressource connue du service. */
public class RessourceAbsenteException extends ReservationException{
    public RessourceAbsenteException(String message){
        super(message);
    }
}
