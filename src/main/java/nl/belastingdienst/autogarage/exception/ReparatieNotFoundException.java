package nl.belastingdienst.autogarage.exception;

public class ReparatieNotFoundException extends RuntimeException{
    public ReparatieNotFoundException(Long id){
        super("Geen reparatie gevonden met id: " + id);
    }
}
