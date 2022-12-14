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
@RequestMapping("/onderdeel")
public class OnderdeelController {

    @Autowired
    private PartService onderdeelService;

    @GetMapping
    public ResponseEntity<List<PartDto>> alleOnderdelen(){
        return ResponseEntity.ok(onderdeelService.allParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartDto> onderdeelOpId(@PathVariable Long id){
        return ResponseEntity.ok(onderdeelService.partById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuwOnderdeel(@RequestBody Part part){
        PartDto onderdeelDto = onderdeelService.newPart(part);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(onderdeelDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOnderdeel(@PathVariable Long id, @RequestBody Part nieuwPart){
        onderdeelService.updatePart(id, nieuwPart);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderOnderdeel(@PathVariable Long id){
        onderdeelService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
