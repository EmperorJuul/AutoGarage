package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.RepairDto;
import nl.belastingdienst.autogarage.exception.PartNotFoundException;
import nl.belastingdienst.autogarage.exception.RepairNotFoundException;
import nl.belastingdienst.autogarage.model.Part;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.repository.PartRepository;
import nl.belastingdienst.autogarage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private PartRepository partRepository;

    public List<RepairDto> allRepairs(){
        List<Repair> repairList = repairRepository.findAll();
        List<RepairDto> reparatieDtoList = new ArrayList<>();
        for(Repair repair : repairList){
            reparatieDtoList.add(fromRepairToDto(repair));
        }
        return reparatieDtoList;
    }

    public RepairDto repairById(Long id){
        return fromRepairToDto(repairRepository.findById(id).orElseThrow(() -> new RepairNotFoundException(id)));
    }

    public RepairDto newRepair(RepairDto repairDto){
        Repair repair = fromDtoToRepair(repairDto);
        repairRepository.save(repair);
        return fromRepairToDto(repair);
    }

    public void updateRepair(Long id, RepairDto repairInputDto) {
        Repair repair = repairRepository.findById(id).orElseThrow(() -> new RepairNotFoundException(id));
        repair.setName(repairInputDto.getName());
        repair.setPrice(repairInputDto.getPrice());
        repairRepository.save(repair);
    }

    public void deleteRepair(Long id){
        repairRepository.deleteById(id);
    }

    private RepairDto fromRepairToDto(Repair repair){
        RepairDto repairDto = new RepairDto(repair.getName(), repair.getPrice());
        repairDto.setId(repair.getId());
        return repairDto;
    }

    private Repair fromDtoToRepair(RepairDto repairDto){
        Repair repair = new Repair(repairDto.getName(), repairDto.getPrice());
        repair.setId(repairDto.getId());
        return repair;
    }

    public void addPart(Long repairId, Long partID){
        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new RepairNotFoundException(repairId));
        Part part = partRepository.findById(partID).orElseThrow(() -> new PartNotFoundException(partID));
        repair.setPart(part);
        part.addToRepairList(repair);
        partRepository.save(part);
        repairRepository.save(repair);
    }

    public void removePart(Long repairId){
        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new RepairNotFoundException(repairId));
        Part part = repair.getPart();
        repair.setPart(null);
        part.removeFromRepairList(repair);
        repairRepository.save(repair);
        partRepository.save(part);
    }
}
