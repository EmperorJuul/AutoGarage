package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Bon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Klant klant;
    @ManyToOne
    private Auto auto;
    @ManyToMany
    private List<Reparatie> reparaties;
    private double totaalprijs;

    public Bon() {
    }

    public Bon(double totaalprijs) {
        this.totaalprijs = totaalprijs;
    }
}
