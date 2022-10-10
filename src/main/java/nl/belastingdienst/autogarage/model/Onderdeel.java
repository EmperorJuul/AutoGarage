package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "onderdelen")
public class Onderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    private String merk;

    public Onderdeel() {
    }

    public Onderdeel(String naam, String merk) {
        this.naam = naam;
        this.merk = merk;
    }
}
