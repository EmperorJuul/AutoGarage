package nl.belastingdienst.autogarage.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import nl.belastingdienst.autogarage.model.Authority;

import java.util.Set;

@Getter
@Setter
public class GebruikerDto {

    public String gebruikersnaam;
    public Boolean ingeschakeld;
    public String email;
    @JsonSerialize
    public Set<Authority> authorities;
}
