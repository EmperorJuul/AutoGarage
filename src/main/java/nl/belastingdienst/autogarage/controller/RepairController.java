package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.RepairDto;
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
    public ResponseEntity<List<RepairDto>> allRepairs() {
        return ResponseEntity.ok(repairService.allRepairs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairDto> repairById(@PathVariable Long id) {
        return ResponseEntity.ok(repairService.repairById(id));
    }

    @PostMapping
    public ResponseEntity<Object> newRepair(@RequestBody RepairDto repairInputDto) {
        RepairDto repairOutputDto = repairService.newRepair(repairInputDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(repairOutputDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepair(@PathVariable Long id, @RequestBody RepairDto repairInputDto) {
        repairService.updateRepair(id, repairInputDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRepair(@PathVariable Long id) {
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/part/{repairId}/{partId}")
    public ResponseEntity<Object> addPart(@PathVariable Long repairId, @PathVariable Long partId) {
        repairService.addPart(repairId, partId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/part/{repairId}")
    public ResponseEntity<Object> removePart(@PathVariable Long repairId){
        repairService.removePart(repairId);
        return ResponseEntity.noContent().build();
    }
}
