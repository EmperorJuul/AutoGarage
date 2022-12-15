package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.PartDto;
import nl.belastingdienst.autogarage.exception.PartNotFoundException;
import nl.belastingdienst.autogarage.model.Part;
import nl.belastingdienst.autogarage.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public List<PartDto> allParts(){
        List<Part> partList = partRepository.findAll();
        List<PartDto> partDtoList = new ArrayList<>();
        for(Part part : partList){
            partDtoList.add(FromPartToDto(part));
        }
        return partDtoList;
    }

    public PartDto partById(Long id){
        return FromPartToDto(partRepository.findById(id).orElseThrow(() -> new PartNotFoundException(id)));
    }

    public PartDto newPart(PartDto partInputDto){
        Part part = fromDtoToPart(partInputDto);
        partRepository.save(part);
        return FromPartToDto(part);
    }

    public void updatePart(Long id, PartDto partInputDto){
        Part originalPart = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException(id));
        Part newPart = fromDtoToPart(partInputDto);
        newPart.setId(originalPart.getId());
        partRepository.save(newPart);
    }

    public void deletePart(Long id){
        partRepository.deleteById(id);
    }

    private PartDto FromPartToDto(Part part){
        PartDto partDto = new PartDto(part.getName(), part.getBrand());
        partDto.setId(part.getId());
        return partDto;
    }

    private Part fromDtoToPart(PartDto partDto){
        Part part = new Part(partDto.getName(), partDto.getBrand());
        return part;
    }
}
