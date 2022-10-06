package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
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
