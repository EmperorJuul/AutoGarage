package nl.belastingdienst.autogarage.exception;

public class OnderdeelNotFoundException extends RuntimeException{
    public OnderdeelNotFoundException(Long id){
        super("Geen onderdeel gevonden met id: " + id);
    }
}
