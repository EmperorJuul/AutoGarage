package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String brand;

    @OneToMany
    private List<Repair> reparatieList;

    public Part() {
    }

    public Part(String naam, String merk) {
        this.name = naam;
        this.brand = merk;
    }
}
