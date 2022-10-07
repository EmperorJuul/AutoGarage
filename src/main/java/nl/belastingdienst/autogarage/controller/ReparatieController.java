package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.ReparatieDto;
import nl.belastingdienst.autogarage.model.Reparatie;
import nl.belastingdienst.autogarage.service.ReparatieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> nieuweReparatie(@RequestBody Reparatie reparatie){
        reparatieService.nieuweReparatie(reparatie);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReparatie(@PathVariable Long id, @RequestBody Reparatie nieuweReparatie){
        reparatieService.updateReparatie(id, nieuweReparatie);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderReparatie(@PathVariable Long id){
        reparatieService.verwijderReparatie(id);
        return ResponseEntity.ok().build();
    }
}
