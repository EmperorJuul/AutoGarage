package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.PartDto;
import nl.belastingdienst.autogarage.model.Part;
import nl.belastingdienst.autogarage.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping
    public ResponseEntity<List<PartDto>> allParts(){
        return ResponseEntity.ok(partService.allParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartDto> partById(@PathVariable Long id){
        return ResponseEntity.ok(partService.partById(id));
    }

    @PostMapping
    public ResponseEntity<Object> newPart(@RequestBody Part part){
        PartDto partDto = partService.newPart(part);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(partDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePart(@PathVariable Long id, @RequestBody Part newPart){
        partService.updatePart(id, newPart);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePart(@PathVariable Long id){
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
