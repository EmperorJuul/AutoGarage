package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public Afspraak() {
    }

    public Afspraak(LocalDateTime beginAfspraak, LocalDateTime eindeAfspraak) {
        this.beginAfspraak = beginAfspraak;
        this.eindeAfspraak = eindeAfspraak;
    }
}
