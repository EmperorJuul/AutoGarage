package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KlantDto {

    @NotNull
    private Long id;
    private String voornaam;
    private String achternaam;
    @Length(min = 10, max = 12)
    private String telefoonnummer;
    @Email
    private String emailAdres;

    public KlantDto(String voornaam, String achternaam, String telefoonnummer, String emailAdres) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoonnummer = telefoonnummer;
        this.emailAdres = emailAdres;
    }
}
