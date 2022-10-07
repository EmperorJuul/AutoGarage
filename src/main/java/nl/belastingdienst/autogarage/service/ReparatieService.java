package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.ReparatieDto;
import nl.belastingdienst.autogarage.exception.NotFoundException;
import nl.belastingdienst.autogarage.model.Reparatie;
import nl.belastingdienst.autogarage.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReparatieService {

    @Autowired
    private ReparatieRepository reparatieRepository;

    public List<ReparatieDto> alleReparaties(){
        List<Reparatie> reparatieList = reparatieRepository.findAll();
        List<ReparatieDto> reparatieDtoList = new ArrayList<>();
        for(Reparatie reparatie : reparatieList){
            reparatieDtoList.add(vanReparatieNaarReparatieDto(reparatie));
        }
        return reparatieDtoList;
    }

    public ReparatieDto reparatieOpId(Long id){
        return vanReparatieNaarReparatieDto(reparatieRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen reparatie gevonden met dit id")));
    }

    public ReparatieDto nieuweReparatie(Reparatie reparatie){
        reparatieRepository.save(reparatie);
        return vanReparatieNaarReparatieDto(reparatie);
    }

    public void updateReparatie(Long id, Reparatie nieuweReparatie){
        Reparatie reparatie = reparatieRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen reparatie gevonden met dit id"));
        nieuweReparatie.setId(reparatie.getId());
        reparatieRepository.save(nieuweReparatie);
    }

    public void verwijderReparatie(Long id){
        reparatieRepository.deleteById(id);
    }

    private ReparatieDto vanReparatieNaarReparatieDto(Reparatie reparatie){
        ReparatieDto reparatieDto = new ReparatieDto(reparatie.getNaam(), reparatie.getPrijs());
        reparatieDto.setId(reparatie.getId());
        return reparatieDto;
    }
}
