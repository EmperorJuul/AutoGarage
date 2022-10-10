package nl.belastingdienst.autogarage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "autos")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String merk;
    private String model;
    private int bouwjaar;
    private String kenteken;

    @ManyToMany
    private List<Klant> klantList;

    @OneToMany
    private List<Afspraak> afspraakList;

    public Auto() {
    }

    public Auto(String merk, String model, int bouwjaar, String kenteken) {
        this.merk = merk;
        this.model = model;
        this.bouwjaar = bouwjaar;
        this.kenteken = kenteken;
    }
}
