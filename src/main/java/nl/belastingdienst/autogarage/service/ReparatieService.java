package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.RepairDto;
import nl.belastingdienst.autogarage.exception.ReparatieNotFoundException;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReparatieService {

    @Autowired
    private ReparatieRepository reparatieRepository;

    public List<RepairDto> alleReparaties(){
        List<Repair> reparatieList = reparatieRepository.findAll();
        List<RepairDto> reparatieDtoList = new ArrayList<>();
        for(Repair reparatie : reparatieList){
            reparatieDtoList.add(vanReparatieNaarReparatieDto(reparatie));
        }
        return reparatieDtoList;
    }

    public RepairDto reparatieOpId(Long id){
        return vanReparatieNaarReparatieDto(reparatieRepository.findById(id).orElseThrow(() -> new ReparatieNotFoundException(id)));
    }

    public RepairDto nieuweReparatie(Repair reparatie){
        reparatieRepository.save(reparatie);
        return vanReparatieNaarReparatieDto(reparatie);
    }

    public void updateReparatie(Long id, Repair nieuweReparatie){
        Repair reparatie = reparatieRepository.findById(id).orElseThrow(() -> new ReparatieNotFoundException(id));
        nieuweReparatie.setId(reparatie.getId());
        reparatieRepository.save(nieuweReparatie);
    }

    public void verwijderReparatie(Long id){
        reparatieRepository.deleteById(id);
    }

    private RepairDto vanReparatieNaarReparatieDto(Repair reparatie){
        RepairDto reparatieDto = new RepairDto(reparatie.getName(), reparatie.getPrice());
        reparatieDto.setId(reparatie.getId());
        return reparatieDto;
    }
}
