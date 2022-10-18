package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.GebruikerDto;
import nl.belastingdienst.autogarage.exception.GebruikerNotFoundException;
import nl.belastingdienst.autogarage.model.Authority;
import nl.belastingdienst.autogarage.model.Gebruiker;
import nl.belastingdienst.autogarage.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Set<Authority> getAuthorities(String gebruikersnaam){
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam));
        GebruikerDto gebruikerDto = vanGebruikerNaarGebruikerDto(gebruiker);
        return gebruikerDto.getAuthorities();
    }

    public void addAuthority(String gebruikersnaam, String authority){
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam));
        gebruiker.addAuthority(new Authority(gebruikersnaam, authority));
        gebruikerRepository.save(gebruiker);
    }

    public void removeAuthority(String gebruikersnaam, String authority){
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam));
        Authority authorityToRemove = gebruiker.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        gebruiker.removeAuthority(authorityToRemove);
    }

    private GebruikerDto vanGebruikerNaarGebruikerDto(Gebruiker gebruiker){
        GebruikerDto gebruikerDto = new GebruikerDto(gebruiker.getGebruikersnaam(), gebruiker.getAuthorities());
        return gebruikerDto;
    }


}
