package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.belastingdienst.autogarage.model.Authority;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UserOutputDto {

    @NotNull
    private String username;

    private Set<Authority> authorities;

    public UserOutputDto(String username, Set<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }
}
