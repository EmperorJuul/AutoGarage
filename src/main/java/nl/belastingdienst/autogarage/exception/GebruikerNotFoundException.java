package nl.belastingdienst.autogarage.exception;

public class GebruikerNotFoundException extends RuntimeException{
    public GebruikerNotFoundException(String gebruikersnaam){
        super("Geen gebruiker gevonden met gebruikersnaam:" + gebruikersnaam);
    }
}
