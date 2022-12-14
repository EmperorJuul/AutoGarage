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
@RequestMapping("/reparatie")
public class ReparatieController {

    @Autowired
    private RepairService reparatieService;

    @GetMapping
    public ResponseEntity<List<RepairDto>> alleReparaties(){
        return ResponseEntity.ok(reparatieService.allRepairs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairDto> reparatieOpId(@PathVariable Long id){
        return ResponseEntity.ok(reparatieService.repairById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweReparatie(@RequestBody Repair reparatie){
        RepairDto reparatieDto = reparatieService.newRepair(reparatie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(reparatieDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReparatie(@PathVariable Long id, @RequestBody Repair nieuweReparatie){
        reparatieService.updateRepair(id, nieuweReparatie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderReparatie(@PathVariable Long id){
        reparatieService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}
