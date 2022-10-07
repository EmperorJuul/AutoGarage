package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.KlantDto;
import nl.belastingdienst.autogarage.exception.NotFoundException;
import nl.belastingdienst.autogarage.model.Klant;
import nl.belastingdienst.autogarage.repository.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlantService {

    @Autowired
    private KlantRepository klantRepository;

    public List<KlantDto> alleKlanten(){
        List<Klant> klantList = klantRepository.findAll();
        List<KlantDto> klantDtoList = new ArrayList<>();
        for(Klant klant : klantList){
            klantDtoList.add(vanKlantNaarKlantDto(klant));
        }
        return klantDtoList;
    }

    public KlantDto klantOpId(Long id){
        return vanKlantNaarKlantDto(klantRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen klant gevonden met dit id")));
    }

    public void nieuweKlant(Klant klant){
        klantRepository.save(klant);
    }

    public void updateKlant(Long id, Klant nieuweKlant){
        Klant klant = klantRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen klant gevonden met dit id"));
        nieuweKlant.setId(klant.getId());
        klantRepository.save(nieuweKlant);
    }

    public void verwijderKlant(Long id){
        klantRepository.deleteById(id);
    }

    private KlantDto vanKlantNaarKlantDto(Klant klant){
        KlantDto klantDto = new KlantDto(klant.getVoornaam(), klant.getAchternaam(), klant.getTelefoonnummer(), klant.getEmailAdres());
        klantDto.setId(klant.getId());
        return klantDto;
    }
}
