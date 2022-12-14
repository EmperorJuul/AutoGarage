package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.ReparatieDto;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.service.ReparatieService;
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
    private ReparatieService reparatieService;

    @GetMapping
    public ResponseEntity<List<ReparatieDto>> alleReparaties(){
        return ResponseEntity.ok(reparatieService.alleReparaties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReparatieDto> reparatieOpId(@PathVariable Long id){
        return ResponseEntity.ok(reparatieService.reparatieOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweReparatie(@RequestBody Repair reparatie){
        ReparatieDto reparatieDto = reparatieService.nieuweReparatie(reparatie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(reparatieDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReparatie(@PathVariable Long id, @RequestBody Repair nieuweReparatie){
        reparatieService.updateReparatie(id, nieuweReparatie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderReparatie(@PathVariable Long id){
        reparatieService.verwijderReparatie(id);
        return ResponseEntity.noContent().build();
    }
}
