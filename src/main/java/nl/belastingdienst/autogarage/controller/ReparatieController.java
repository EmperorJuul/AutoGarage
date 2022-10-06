package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.model.Reparatie;
import nl.belastingdienst.autogarage.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reparatie")
public class ReparatieController {

    @Autowired
    private ReparatieRepository reparatieRepository;

    @GetMapping
    public ResponseEntity<List<Reparatie>> alleReparaties(){
        List<Reparatie> reparatieList = new ArrayList<>();
        for(Reparatie reparatie : reparatieRepository.findAll()){
            reparatieList.add(reparatie);
        }
        return ResponseEntity.ok(reparatieList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reparatie>> reparatieOpId(@PathVariable Long id){
        return ResponseEntity.ok(reparatieRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweReparatie(@RequestBody Reparatie reparatie){
        reparatieRepository.save(reparatie);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReparatie(@PathVariable Long id, @RequestBody Reparatie nieuweReparatie){
        Optional<Reparatie> reparatie = reparatieRepository.findById(id);
        nieuweReparatie.setId(reparatie.get().getId());
        reparatieRepository.save(nieuweReparatie);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderReparatie(@PathVariable Long id){
        reparatieRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
