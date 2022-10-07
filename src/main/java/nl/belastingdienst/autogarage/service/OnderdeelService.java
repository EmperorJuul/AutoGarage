package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.OnderdeelDto;
import nl.belastingdienst.autogarage.exception.NotFoundException;
import nl.belastingdienst.autogarage.model.Onderdeel;
import nl.belastingdienst.autogarage.repository.OnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnderdeelService {

    @Autowired
    private OnderdeelRepository onderdeelRepository;

    public List<OnderdeelDto> alleOnderdelen(){
        List<Onderdeel> onderdeelList = onderdeelRepository.findAll();
        List<OnderdeelDto> onderdeelDtoList = new ArrayList<>();
        for(Onderdeel onderdeel : onderdeelList){
            onderdeelDtoList.add(vanOnderdeelNaarOnderdeelDto(onderdeel));
        }
        return onderdeelDtoList;
    }

    public OnderdeelDto onderdeelOpId(Long id){
        return vanOnderdeelNaarOnderdeelDto(onderdeelRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen onderdeel gevonden met dit id")));
    }

    public void nieuwOnderdeel(Onderdeel onderdeel){
        onderdeelRepository.save(onderdeel);
    }

    public void updateOnderdeel(Long id, Onderdeel nieuwOnderdeel){
        Onderdeel onderdeel = onderdeelRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen onderdeel gevonden met dit id"));
        nieuwOnderdeel.setId(onderdeel.getId());
        onderdeelRepository.save(nieuwOnderdeel);
    }

    public void verwijderOnderdeel(Long id){
        onderdeelRepository.deleteById(id);
    }

    private OnderdeelDto vanOnderdeelNaarOnderdeelDto(Onderdeel onderdeel){
        OnderdeelDto onderdeelDto = new OnderdeelDto(onderdeel.getNaam(), onderdeel.getMerk());
        onderdeelDto.setId(onderdeel.getId());
        return onderdeelDto;
    }
}
