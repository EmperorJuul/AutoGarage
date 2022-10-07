package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AutoDto;
import nl.belastingdienst.autogarage.exception.NotFoundException;
import nl.belastingdienst.autogarage.model.Auto;
import nl.belastingdienst.autogarage.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public List<AutoDto> alleAutos(){
        List<Auto> autoList = autoRepository.findAll();
        List<AutoDto> autoDtoList = new ArrayList<>();
        for(Auto auto : autoList){
            autoDtoList.add(vanAutoNaarAutoDto(auto));
        }
        return autoDtoList;
    }

    public AutoDto autoOpId(Long id){
        return vanAutoNaarAutoDto(autoRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen auto gevonden met dit id")));
    }

    public void nieuweAuto(Auto auto){
        autoRepository.save(auto);
    }

    public void updateAuto(Long id, Auto nieuweAuto){
        Auto auto = autoRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen auto gevonden met dit id"));
        nieuweAuto.setId(auto.getId());
        autoRepository.save(nieuweAuto);
    }

    public void verwijderAuto(Long id){
        autoRepository.deleteById(id);
    }

    private AutoDto vanAutoNaarAutoDto(Auto auto){
        AutoDto autoDto = new AutoDto(auto.getMerk(), auto.getModel(), auto.getBouwjaar(), auto.getKenteken());
        autoDto.setId(auto.getId());
        return autoDto;
    }
}
