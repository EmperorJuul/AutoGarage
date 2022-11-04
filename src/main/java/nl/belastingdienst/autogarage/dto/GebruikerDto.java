package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.belastingdienst.autogarage.model.Authority;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class GebruikerDto {

    @NotNull
    private String gebruikersnaam;

    private Set<Authority> authorities;

    public GebruikerDto(String gebruikersnaam, Set<Authority> authorities) {
        this.gebruikersnaam = gebruikersnaam;
        this.authorities = authorities;
    }
}
