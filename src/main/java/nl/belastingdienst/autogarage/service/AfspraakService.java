package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AfspraakDto;
import nl.belastingdienst.autogarage.exception.NotFoundException;
import nl.belastingdienst.autogarage.model.Afspraak;
import nl.belastingdienst.autogarage.repository.AfspraakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AfspraakService {

    @Autowired
    private AfspraakRepository afspraakRepository;

    public List<AfspraakDto> alleAfspraken(){
        List<Afspraak> afspraakList = afspraakRepository.findAll();
        List<AfspraakDto> afspraakDtoList = new ArrayList<>();
        for(Afspraak afspraak : afspraakList){
            afspraakDtoList.add(vanAfspraakNaarAfspraakDto(afspraak));
        }
        return afspraakDtoList;
    }

    public AfspraakDto afspraakOpId(Long id){
        return vanAfspraakNaarAfspraakDto(afspraakRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen afspraak gevonden met dit id")));
    }

    public void nieuweAfspraak(Afspraak afspraak){
        afspraakRepository.save(afspraak);
    }

    public void updateAfspraak(Long id, Afspraak nieuweAfspraak){
        Afspraak afspraak = afspraakRepository.findById(id).orElseThrow(() -> new NotFoundException("Geen afspraak gevonden met dit id"));
        nieuweAfspraak.setId(afspraak.getId());
        afspraakRepository.save(nieuweAfspraak);
    }

    public void verwijderAfspraak(Long id){
        afspraakRepository.deleteById(id);
    }

    private AfspraakDto vanAfspraakNaarAfspraakDto(Afspraak afspraak){
        AfspraakDto afspraakDto = new AfspraakDto(afspraak.getBeginAfspraak(), afspraak.getEindeAfspraak());
        afspraakDto.setId(afspraak.getId());
        return afspraakDto;
    }
}
