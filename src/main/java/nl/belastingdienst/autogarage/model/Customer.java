package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String emailAdress;

    @ManyToMany
    private List<Car> carList;

    @OneToMany
    private List<Appointment> appointmentList;

    public Customer() {
    }

    public Customer(String firstname, String surname, String phoneNumber, String emailAdress) {
        this.firstname = firstname;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
    }

    public void addToCarlist(Car car){
        carList.add(car);
    }

    public void removeFromCarList(Car car){
        carList.remove(car);
    }

    public void addToAppointmentList(Appointment appointment){
        appointmentList.add(appointment);
    }

    public void removeFromAppointmentList(Appointment appointment){
        appointmentList.remove(appointment);
    }
}
