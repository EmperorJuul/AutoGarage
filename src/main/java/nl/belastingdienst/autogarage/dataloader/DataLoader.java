package nl.belastingdienst.autogarage.dataloader;

import nl.belastingdienst.autogarage.model.*;
import nl.belastingdienst.autogarage.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader {

    private AfspraakRepository afspraakRepository;
    private AutoRepository autoRepository;
    private KlantRepository klantRepository;
    private OnderdeelRepository onderdeelRepository;
    private ReparatieRepository reparatieRepository;

    public DataLoader(AfspraakRepository afspraakRepository, AutoRepository autoRepository, KlantRepository klantRepository, OnderdeelRepository onderdeelRepository, ReparatieRepository reparatieRepository) {
        this.afspraakRepository = afspraakRepository;
        this.autoRepository = autoRepository;
        this.klantRepository = klantRepository;
        this.onderdeelRepository = onderdeelRepository;
        this.reparatieRepository = reparatieRepository;
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

        Klant klant1 = new Klant("Juul", "Konings", "0612345678", "j.konings@belastingdienst.nl");
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
