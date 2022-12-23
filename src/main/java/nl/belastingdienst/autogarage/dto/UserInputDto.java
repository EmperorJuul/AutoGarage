package nl.belastingdienst.autogarage.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserInputDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserInputDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
