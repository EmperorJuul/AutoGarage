package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.OnderdeelDto;
import nl.belastingdienst.autogarage.exception.OnderdeelNotFoundException;
import nl.belastingdienst.autogarage.model.Part;
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
        List<Part> partList = onderdeelRepository.findAll();
        List<OnderdeelDto> onderdeelDtoList = new ArrayList<>();
        for(Part part : partList){
            onderdeelDtoList.add(vanOnderdeelNaarOnderdeelDto(part));
        }
        return onderdeelDtoList;
    }

    public OnderdeelDto onderdeelOpId(Long id){
        return vanOnderdeelNaarOnderdeelDto(onderdeelRepository.findById(id).orElseThrow(() -> new OnderdeelNotFoundException(id)));
    }

    public OnderdeelDto nieuwOnderdeel(Part part){
        onderdeelRepository.save(part);
        return vanOnderdeelNaarOnderdeelDto(part);
    }

    public void updateOnderdeel(Long id, Part nieuwPart){
        Part part = onderdeelRepository.findById(id).orElseThrow(() -> new OnderdeelNotFoundException(id));
        nieuwPart.setId(part.getId());
        onderdeelRepository.save(nieuwPart);
    }

    public void verwijderOnderdeel(Long id){
        onderdeelRepository.deleteById(id);
    }

    private OnderdeelDto vanOnderdeelNaarOnderdeelDto(Part part){
        OnderdeelDto onderdeelDto = new OnderdeelDto(part.getName(), part.getBrand());
        onderdeelDto.setId(part.getId());
        return onderdeelDto;
    }
}
