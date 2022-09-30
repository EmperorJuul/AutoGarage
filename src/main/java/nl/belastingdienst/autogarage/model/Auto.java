package nl.belastingdienst.autogarage.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String merk;
    private String model;
    private int bouwjaar;
    private String kenteken;

    public Auto() {
    }

    public Auto(String merk, String model, int bouwjaar, String kenteken) {
        this.merk = merk;
        this.model = model;
        this.bouwjaar = bouwjaar;
        this.kenteken = kenteken;
    }
}
