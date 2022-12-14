package nl.belastingdienst.autogarage.dataloader;

import nl.belastingdienst.autogarage.model.*;
import nl.belastingdienst.autogarage.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader {

    private AppointmentRepository appointmentRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private PartRepository partRepository;
    private RepairRepository repairRepository;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public DataLoader(AppointmentRepository appointmentRepository,
                      CarRepository carRepository,
                      CustomerRepository customerRepository,
                      PartRepository partRepository,
                      RepairRepository repairRepository,
                      UserRepository userRepository,
                      AuthorityRepository authorityRepository){
        this.appointmentRepository = appointmentRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.partRepository = partRepository;
        this.repairRepository = repairRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        load();
    }

    public void load(){
        Appointment appointment1 = new Appointment(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        Appointment appointment2 = new Appointment(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);

        Car car1 = new Car("Opel", "Corsa", 2006, "DF-45-A4");
        Car car2 = new Car("Volkswagen", "ID4", 2021, "23-HC-6G");
        carRepository.save(car1);
        carRepository.save(car2);

        User user1 = new User("Mechanic", passwordEncoder().encode("Mechanic"));
        User user2 = new User("Backoffice", passwordEncoder().encode("Backoffice"));
        User user3 = new User("Admin", passwordEncoder().encode("Admin"));
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        Authority authority1 = new Authority("Mechanic", "ROLE_MECHANIC");
        Authority authority2 = new Authority("Backoffice", "ROLE_BACKOFFICE");
        Authority authority3 = new Authority("Admin", "ROLE_ADMIN");
        authorityRepository.save(authority1);
        authorityRepository.save(authority2);
        authorityRepository.save(authority3);

        Customer customer1 = new Customer("Juul", "Konings", "0612345678", "jk@outlook.com");
        Customer customer2 = new Customer("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Part part1 = new Part("Tire", "Michelin");
        Part part2 = new Part("Window", "Noordglas");
        partRepository.save(part1);
        partRepository.save(part2);

        Repair repair1 = new Repair("Change tires", 104);
        Repair repair2 = new Repair("Change oil", 90);
        repairRepository.save(repair1);
        repairRepository.save(repair2);

    }
}
