package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatieVerzoek {

    private String gebruikersnaam;
    private String wachtwoord;

    public AuthenticatieVerzoek() {
    }

    public AuthenticatieVerzoek(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }
}
