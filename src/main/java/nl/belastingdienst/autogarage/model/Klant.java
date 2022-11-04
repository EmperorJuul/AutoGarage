package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "klanten")
public class Klant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String voornaam;
    private String achternaam;
    private String telefoonnummer;
    private String emailAdres;

    @ManyToMany
    private List<Auto> autoList;

    @OneToMany
    private List<Afspraak> afspraakList;

    public Klant() {
    }

    public Klant(String voornaam, String achternaam, String telefoonnummer, String emailAdres) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoonnummer = telefoonnummer;
        this.emailAdres = emailAdres;
    }
}
