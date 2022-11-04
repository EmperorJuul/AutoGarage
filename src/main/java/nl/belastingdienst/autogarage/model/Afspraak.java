package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "afspraken")
public class Afspraak {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime beginAfspraak;
    private LocalDateTime eindeAfspraak;

    @ManyToMany
    private List<Reparatie> reparatie;

    @ManyToOne
    private Auto auto;

    @ManyToOne
    private Klant klant;

    public Afspraak() {
    }

    public Afspraak(LocalDateTime beginAfspraak, LocalDateTime eindeAfspraak) {
        this.beginAfspraak = beginAfspraak;
        this.eindeAfspraak = eindeAfspraak;
    }
}
