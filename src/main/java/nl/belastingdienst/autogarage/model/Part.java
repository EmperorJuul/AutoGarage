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
    private List<Repair> repairList;

    public Part() {
    }

    public Part(String naam, String merk) {
        this.name = naam;
        this.brand = merk;
    }

    public void addToRepairList(Repair repair){
        repairList.add(repair);
    }

    public void removeFromRepairList(Repair repair){
        repairList.remove(repair);
    }
}
