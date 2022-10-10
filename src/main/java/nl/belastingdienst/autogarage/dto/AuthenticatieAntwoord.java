package nl.belastingdienst.autogarage.dto;

import lombok.Getter;

@Getter
public class AuthenticatieAntwoord {

    private final String jwt;

    public AuthenticatieAntwoord(String jwt) {
        this.jwt = jwt;
    }
}
