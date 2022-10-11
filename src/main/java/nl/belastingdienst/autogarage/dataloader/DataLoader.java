package nl.belastingdienst.autogarage.dataloader;

import nl.belastingdienst.autogarage.model.*;
import nl.belastingdienst.autogarage.repository.*;
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

    private AuthoriteitRepository authoriteitRepository;

    private PasswordEncoder passwordEncoder;

    public DataLoader(AfspraakRepository afspraakRepository,
                      AutoRepository autoRepository,
                      KlantRepository klantRepository,
                      OnderdeelRepository onderdeelRepository,
                      ReparatieRepository reparatieRepository,
                      GebruikerRepository gebruikerRepository,
                      AuthoriteitRepository authoriteitRepository,
                      PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.afspraakRepository = afspraakRepository;
        this.autoRepository = autoRepository;
        this.klantRepository = klantRepository;
        this.onderdeelRepository = onderdeelRepository;
        this.reparatieRepository = reparatieRepository;
        this.gebruikerRepository = gebruikerRepository;
        this.authoriteitRepository = authoriteitRepository;
        load();
    }

    public void load(){
        Afspraak afspraak1 = new Afspraak(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        Afspraak afspraak2 = new Afspraak(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        afspraakRepository.save(afspraak1);
        afspraakRepository.save(afspraak2);

        Auto auto1 = new Auto("Opel", "Corsa", 2006, "DF-45-A4");
        Auto auto2 = new Auto("Volkswagen", "ID4", 2021, "23-HC-6G");
        autoRepository.save(auto1);
        autoRepository.save(auto2);

        Gebruiker gebruiker1 = new Gebruiker("Juul", "$2a$12$05GnLXeX7oAFIHZiiWW5jeuuWCRoGwB7vBW0.2/HiHjg5x5etX2A6", true, "jk@hotmail.com");
        gebruikerRepository.save(gebruiker1);

        Authoriteit authoriteit1 = new Authoriteit("Juul", "ROLE_ADMIN");
        authoriteitRepository.save(authoriteit1);

        Klant klant1 = new Klant("Juul", "Konings", "0612345678", "jk@outlook.com");
        Klant klant2 = new Klant("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klantRepository.save(klant1);
        klantRepository.save(klant2);

        Onderdeel onderdeel1 = new Onderdeel("Band", "Michelin");
        Onderdeel onderdeel2 = new Onderdeel("Ruit", "Noordglas");
        onderdeelRepository.save(onderdeel1);
        onderdeelRepository.save(onderdeel2);

        Reparatie reparatie1 = new Reparatie("Banden vervangen", 104);
        Reparatie reparatie2 = new Reparatie("Olie vervangen", 90);
        reparatieRepository.save(reparatie1);
        reparatieRepository.save(reparatie2);

    }
}
