package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.KlantDto;
import nl.belastingdienst.autogarage.model.Klant;
import nl.belastingdienst.autogarage.repository.KlantRepository;
import nl.belastingdienst.autogarage.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/klant")
public class KlantController {

    @Autowired
    private KlantRepository klantRepository;

    @Autowired
    private KlantService klantService;

    @GetMapping
    public ResponseEntity<List<KlantDto>> alleKlanten(){
        return ResponseEntity.ok(klantService.alleKlanten());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KlantDto> KlantOpId(@PathVariable Long id){
        return ResponseEntity.ok(klantService.klantOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweKlant(@RequestBody Klant klant){
        klantService.nieuweKlant(klant);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKlant(@PathVariable Long id, @RequestBody Klant nieuweKlant){
        klantService.updateKlant(id, nieuweKlant);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderKlant(@PathVariable Long id){
        klantService.verwijderKlant(id);
        return ResponseEntity.ok().build();
    }
}
