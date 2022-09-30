package nl.belastingdienst.autogarage.dataloader;

import nl.belastingdienst.autogarage.model.Auto;
import nl.belastingdienst.autogarage.model.Klant;
import nl.belastingdienst.autogarage.model.Onderdeel;
import nl.belastingdienst.autogarage.model.Reparatie;
import nl.belastingdienst.autogarage.repository.*;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private AfspraakRepository afspraakRepository;
    private AutoRepository autoRepository;
    private BonRepository bonRepository;
    private KlantRepository klantRepository;
    private OnderdeelRepository onderdeelRepository;
    private ReparatieRepository reparatieRepository;

    public DataLoader(AfspraakRepository afspraakRepository, AutoRepository autoRepository, BonRepository bonRepository, KlantRepository klantRepository, OnderdeelRepository onderdeelRepository, ReparatieRepository reparatieRepository) {
        this.afspraakRepository = afspraakRepository;
        this.autoRepository = autoRepository;
        this.bonRepository = bonRepository;
        this.klantRepository = klantRepository;
        this.onderdeelRepository = onderdeelRepository;
        this.reparatieRepository = reparatieRepository;
        load();
    }

    public void load(){
        Klant klant1 = new Klant("Juul", "Konings", "0612345678", "j.konings@belastingdienst.nl");
        Klant klant2 = new Klant("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klantRepository.save(klant1);
        klantRepository.save(klant2);

        Auto auto1 = new Auto("Opel", "Corsa", 2006, "DF-45-A4");
        Auto auto2 = new Auto("Volkswagen", "ID4", 2021, "23-HC-6G");
        autoRepository.save(auto1);
        autoRepository.save(auto2);

        Onderdeel onderdeel1 = new Onderdeel("Band", "Michelin");
        Onderdeel onderdeel2 = new Onderdeel("Ruit", "Noordglas");
        onderdeelRepository.save(onderdeel1);
        onderdeelRepository.save(onderdeel2);

    }
}
