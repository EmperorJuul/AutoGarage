package nl.belastingdienst.autogarage.exception;

public class KlantNotFoundException extends RuntimeException{
    public KlantNotFoundException(Long id){
        super("Geen klant gevonden met id: " + id);
    }
}
