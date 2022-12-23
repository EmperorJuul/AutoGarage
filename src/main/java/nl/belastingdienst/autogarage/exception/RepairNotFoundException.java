package nl.belastingdienst.autogarage.exception;

public class RepairNotFoundException extends RuntimeException{
    public RepairNotFoundException(Long id){
        super("No repair found with id: " + id);
    }

    public RepairNotFoundException(String text){super(text);}
}
