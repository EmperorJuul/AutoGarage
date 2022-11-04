package nl.belastingdienst.autogarage.exception;

public class AfspraakNotFoundException extends RuntimeException{
    public AfspraakNotFoundException(Long id){
        super("Geen afspraak gevonden met id: " + id);
    }
}
