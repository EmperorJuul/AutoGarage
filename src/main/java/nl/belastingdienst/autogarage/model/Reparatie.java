package nl.belastingdienst.autogarage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reparaties")
public class Reparatie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    private double prijs;

    @ManyToMany
    private List<Afspraak> afspraakList;

    @ManyToOne
    private Onderdeel onderdeel;

    public Reparatie() {
    }

    public Reparatie(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }
}
