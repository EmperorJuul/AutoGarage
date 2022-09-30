package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Klant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String voornaam;
    private String achternaam;
    private String telefoonnummer;
    private String emailAdres;

    public Klant() {
    }

    public Klant(String voornaam, String achternaam, String telefoonnummer, String emailAdres) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoonnummer = telefoonnummer;
        this.emailAdres = emailAdres;
    }
}
