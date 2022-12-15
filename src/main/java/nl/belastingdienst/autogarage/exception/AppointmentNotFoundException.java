package nl.belastingdienst.autogarage.exception;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException(Long id){
        super("No appointment found with id: " + id);
    }
}
