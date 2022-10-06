package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.model.Auto;
import nl.belastingdienst.autogarage.model.Klant;
import nl.belastingdienst.autogarage.repository.AutoRepository;
import nl.belastingdienst.autogarage.repository.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/klant")
public class KlantController {

    @Autowired
    private KlantRepository klantRepository;

    @GetMapping
    public ResponseEntity<List<Klant>> alleKlanten(){
        List<Klant> klantList = new ArrayList<>();
        for(Klant klant : klantRepository.findAll()){
            klantList.add(klant);
        }
        return ResponseEntity.ok(klantList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Klant>> KlantOpId(@PathVariable Long id){
        return ResponseEntity.ok(klantRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweKlant(@RequestBody Klant klant){
        klantRepository.save(klant);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKlant(@PathVariable Long id, @RequestBody Klant nieuweKlant){
        Optional<Klant> klant = klantRepository.findById(id);
         nieuweKlant.setId(klant.get().getId());
        klantRepository.save(nieuweKlant);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderKlant(@PathVariable Long id){
        klantRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
