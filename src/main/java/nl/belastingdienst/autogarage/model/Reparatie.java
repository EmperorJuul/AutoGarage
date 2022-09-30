package nl.belastingdienst.autogarage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Reparatie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    @OneToOne
    private Onderdeel onderdeel;
    private double prijs;

    public Reparatie() {
    }

    public Reparatie(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }
}
