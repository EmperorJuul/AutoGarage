package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.RepairDto;
import nl.belastingdienst.autogarage.exception.RepairNotFoundException;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

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

    public RepairDto newRepair(Repair repair){
        repairRepository.save(repair);
        return fromRepairToDto(repair);
    }

    public void updateRepair(Long id, Repair newRepair){
        Repair repair = repairRepository.findById(id).orElseThrow(() -> new RepairNotFoundException(id));
        newRepair.setId(repair.getId());
        repairRepository.save(newRepair);
    }

    public void deleteRepair(Long id){
        repairRepository.deleteById(id);
    }

    private RepairDto fromRepairToDto(Repair repair){
        RepairDto repairDto = new RepairDto(repair.getName(), repair.getPrice());
        repairDto.setId(repair.getId());
        return repairDto;
    }
}
