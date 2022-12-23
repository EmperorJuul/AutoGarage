package nl.belastingdienst.autogarage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;

    @ManyToMany
    private List<Appointment> appointmentList;

    @ManyToOne
    private Part part;

    public Repair() {
    }

    public Repair(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void addToAppointmentList(Appointment appointment){
        appointmentList.add(appointment);
    }

    public void removeFromAppointmentList(Appointment appointment){
        appointmentList.remove(appointment);
    }
}
