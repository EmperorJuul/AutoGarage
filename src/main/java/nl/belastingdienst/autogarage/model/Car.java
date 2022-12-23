package nl.belastingdienst.autogarage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private int year;
    @Column(unique = true)
    private String licenseplate;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<Appointment> appointmentList;

    public Car() {
    }

    public Car(String brand, String model, int year, String licenseplate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licenseplate = licenseplate;
    }

    public void addToAppointmentList(Appointment appointment){
        appointmentList.add(appointment);
    }

    public void removeFromAppointmentList(Appointment appointment){
        appointmentList.remove(appointment);
    }

}
