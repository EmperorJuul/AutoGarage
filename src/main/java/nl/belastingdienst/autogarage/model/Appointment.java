package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startAppointment;
    private LocalDateTime endAppointment;

    @ManyToMany
    private List<Repair> repairList;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    public Appointment() {
    }

    public Appointment(LocalDateTime startAppointment, LocalDateTime endAppointment) {
        this.startAppointment = startAppointment;
        this.endAppointment = endAppointment;
    }

    public void addToRepairList(Repair repair){
        repairList.add(repair);
    }

    public void removeFromRepairList(Repair repair){
        repairList.remove(repair);
    }
}
