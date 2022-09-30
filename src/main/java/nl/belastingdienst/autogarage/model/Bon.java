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
public class Bon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //private Klant klant;
    //private Auto auto;                    toevoegen wanneer relationships worden geimplementeerd
    //private List<Reparatie> reparaties;
    private double totaalprijs;

    public Bon() {
    }

    public Bon(double totaalprijs) {
        this.totaalprijs = totaalprijs;
    }
}
