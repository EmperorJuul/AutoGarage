package nl.belastingdienst.autogarage.exception;

public class AutoNotFoundException extends RuntimeException {
    public AutoNotFoundException(Long id) {
        super("Geen auto gevonden met id: " + id);
    }
}
