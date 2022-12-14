package nl.belastingdienst.autogarage.dataloader;

import nl.belastingdienst.autogarage.model.*;
import nl.belastingdienst.autogarage.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader {

    private AfspraakRepository afspraakRepository;
    private AutoRepository autoRepository;
    private KlantRepository klantRepository;
    private OnderdeelRepository onderdeelRepository;
    private ReparatieRepository reparatieRepository;

    private GebruikerRepository gebruikerRepository;

    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public DataLoader(AfspraakRepository afspraakRepository,
                      AutoRepository autoRepository,
                      KlantRepository klantRepository,
                      OnderdeelRepository onderdeelRepository,
                      ReparatieRepository reparatieRepository,
                      GebruikerRepository gebruikerRepository,
                      AuthorityRepository authorityRepository){
        this.afspraakRepository = afspraakRepository;
        this.autoRepository = autoRepository;
        this.klantRepository = klantRepository;
        this.onderdeelRepository = onderdeelRepository;
        this.reparatieRepository = reparatieRepository;
        this.gebruikerRepository = gebruikerRepository;
        this.authorityRepository = authorityRepository;
        load();
    }

    public void load(){
        Appointment appointment1 = new Appointment(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        Appointment appointment2 = new Appointment(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        afspraakRepository.save(appointment1);
        afspraakRepository.save(appointment2);

        Car car1 = new Car("Opel", "Corsa", 2006, "DF-45-A4");
        Car car2 = new Car("Volkswagen", "ID4", 2021, "23-HC-6G");
        autoRepository.save(car1);
        autoRepository.save(car2);

        User user1 = new User("Monteur", passwordEncoder().encode("Monteur"));
        User user2 = new User("Backoffice", passwordEncoder().encode("Backoffice"));
        User user3 = new User("Admin", passwordEncoder().encode("Admin"));
        gebruikerRepository.save(user1);
        gebruikerRepository.save(user2);
        gebruikerRepository.save(user3);
        Authority authority1 = new Authority("Monteur", "ROLE_MONTEUR");
        Authority authority2 = new Authority("Backoffice", "ROLE_BACKOFFICE");
        Authority authority3 = new Authority("Admin", "ROLE_ADMIN");
        authorityRepository.save(authority1);
        authorityRepository.save(authority2);
        authorityRepository.save(authority3);

        Customer customer1 = new Customer("Juul", "Konings", "0612345678", "jk@outlook.com");
        Customer customer2 = new Customer("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klantRepository.save(customer1);
        klantRepository.save(customer2);

        Part part1 = new Part("Band", "Michelin");
        Part part2 = new Part("Ruit", "Noordglas");
        onderdeelRepository.save(part1);
        onderdeelRepository.save(part2);

        Repair reparatie1 = new Repair("Banden vervangen", 104);
        Repair reparatie2 = new Repair("Olie vervangen", 90);
        reparatieRepository.save(reparatie1);
        reparatieRepository.save(reparatie2);

    }
}
