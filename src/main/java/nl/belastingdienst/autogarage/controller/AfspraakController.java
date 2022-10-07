package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AfspraakDto;
import nl.belastingdienst.autogarage.model.Afspraak;
import nl.belastingdienst.autogarage.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/afspraak")
public class AfspraakController {

    @Autowired
    private AfspraakService afspraakService;

    @GetMapping
    public ResponseEntity<List<AfspraakDto>> alleAfspraken(){
        return ResponseEntity.ok(afspraakService.alleAfspraken());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfspraakDto> afspraakOpId(@PathVariable Long id){
        return ResponseEntity.ok(afspraakService.afspraakOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAfspraak(@RequestBody Afspraak afspraak){
        afspraakService.nieuweAfspraak(afspraak);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAfspraak(@PathVariable Long id, @RequestBody Afspraak nieuweAfspraak){
        afspraakService.updateAfspraak(id, nieuweAfspraak);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAfspraak(@PathVariable Long id){
        afspraakService.verwijderAfspraak(id);
        return ResponseEntity.ok().build();
    }
}
