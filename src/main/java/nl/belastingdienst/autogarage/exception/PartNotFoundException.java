package nl.belastingdienst.autogarage.exception;

public class PartNotFoundException extends RuntimeException{
    public PartNotFoundException(Long id){
        super("No part found with id: " + id);
    }

    public PartNotFoundException(String text){super(text);}
}
