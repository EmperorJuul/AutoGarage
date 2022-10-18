package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.GebruikerDto;
import nl.belastingdienst.autogarage.exception.GebruikerNotFoundException;
import nl.belastingdienst.autogarage.model.Gebruiker;
import nl.belastingdienst.autogarage.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GebruikerService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    public List<GebruikerDto> alleGebruikers(){
        List<Gebruiker> gebruikerList = gebruikerRepository.findAll();
        List<GebruikerDto> gebruikerDtoList = new ArrayList<>();
        for(Gebruiker gebruiker : gebruikerList){
            gebruikerDtoList.add(vanGebruikerNaarGebruikerDto(gebruiker));
        }
        return gebruikerDtoList;
    }

    public GebruikerDto gebruikerOpGebruikersnaam(String gebruikersnaam){
        return vanGebruikerNaarGebruikerDto(gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam)));
    }

    public GebruikerDto nieuweGebruiker(Gebruiker gebruiker){
        gebruikerRepository.save(gebruiker);
        return vanGebruikerNaarGebruikerDto(gebruiker);
    }

    public void updateGebruiker(String gebruikersnaam, Gebruiker nieuweGebruiker){
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam));
        nieuweGebruiker.setGebruikersnaam(gebruiker.getGebruikersnaam());
        gebruikerRepository.save(nieuweGebruiker);
    }

    public void verwijderGebruiker(String gebruikersnaam){
        gebruikerRepository.deleteById(gebruikersnaam);
    }

    private GebruikerDto vanGebruikerNaarGebruikerDto(Gebruiker gebruiker){
        GebruikerDto gebruikerDto = new GebruikerDto(gebruiker.getGebruikersnaam());
        return gebruikerDto;
    }
}
