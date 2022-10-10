package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    private List<Reparatie> reparatieList;

    public Onderdeel() {
    }

    public Onderdeel(String naam, String merk) {
        this.naam = naam;
        this.merk = merk;
    }
}
