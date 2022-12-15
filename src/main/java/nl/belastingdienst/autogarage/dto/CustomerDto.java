package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerDto {

    @NotNull
    private Long id;
    private String firstname;
    private String surname;
    @Length(min = 10, max = 12)
    private String phoneNumber;
    @Email
    private String emailAdress;

    public CustomerDto(String firstname, String surname, String phoneNumber, String emailAdress) {
        this.firstname = firstname;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
    }
}
