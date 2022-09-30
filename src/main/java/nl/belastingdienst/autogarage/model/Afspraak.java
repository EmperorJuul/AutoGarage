package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Afspraak {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate beginDatum;
    private LocalDate eindDatum;

    //klant attribuut toevoegen wanneer relationships worden geimplementeerd

    public Afspraak() {
    }

    public Afspraak(LocalDate beginDatum, LocalDate eindDatum) {
        this.beginDatum = beginDatum;
        this.eindDatum = eindDatum;
    }
}
