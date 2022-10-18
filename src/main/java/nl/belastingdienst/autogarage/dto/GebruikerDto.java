package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GebruikerDto {

    @NotNull
    private String gebruikersnaam;

    public GebruikerDto(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
}
