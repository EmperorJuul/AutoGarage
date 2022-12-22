package nl.belastingdienst.autogarage.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("No car found with id: " + id);
    }

    public CarNotFoundException(String text){super(text);}
}
