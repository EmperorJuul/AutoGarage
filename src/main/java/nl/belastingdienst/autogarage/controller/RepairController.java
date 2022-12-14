package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.RepairDto;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping
    public ResponseEntity<List<RepairDto>> allRepairs(){
        return ResponseEntity.ok(repairService.allRepairs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairDto> repairById(@PathVariable Long id){
        return ResponseEntity.ok(repairService.repairById(id));
    }

    @PostMapping
    public ResponseEntity<Object> newRepair(@RequestBody Repair repair){
        RepairDto repairDto = repairService.newRepair(repair);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(repairDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepair(@PathVariable Long id, @RequestBody Repair newRepair){
        repairService.updateRepair(id, newRepair);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRepair(@PathVariable Long id){
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}
