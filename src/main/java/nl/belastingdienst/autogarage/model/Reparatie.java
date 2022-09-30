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
public class Reparatie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    //private Onderdeel onderdeel;   toevoegen wanneer relationships worden geimplementeerd
    private double prijs;

    public Reparatie() {
    }

    public Reparatie(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }
}
