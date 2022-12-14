package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.belastingdienst.autogarage.model.Authority;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    @NotNull
    private String username;

    private Set<Authority> authorities;

    public UserDto(String username, Set<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }
}
