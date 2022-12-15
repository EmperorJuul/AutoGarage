package nl.belastingdienst.autogarage.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super("No customer found with id: " + id);
    }
}
