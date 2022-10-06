package nl.belastingdienst.autogarage.controller;

import lombok.Getter;
import nl.belastingdienst.autogarage.model.Afspraak;
import nl.belastingdienst.autogarage.repository.AfspraakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/afspraak")
public class AfspraakController {

    @Autowired
    private AfspraakRepository afspraakRepository;

    @GetMapping
    public ResponseEntity<List<Afspraak>> alleAfspraken(){
        List<Afspraak> afspraakList = new ArrayList<>();
        for(Afspraak afspraak : afspraakRepository.findAll()){
            afspraakList.add(afspraak);
        }
        return ResponseEntity.ok(afspraakList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Afspraak>> afspraakOpId(@PathVariable Long id){
        return ResponseEntity.ok(afspraakRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAfspraak(@RequestBody Afspraak afspraak){
        afspraakRepository.save(afspraak);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAfspraak(@PathVariable Long id, @RequestBody Afspraak nieuweAfspraak){
        Optional<Afspraak> afspraak = afspraakRepository.findById(id);
        nieuweAfspraak.setId(afspraak.get().getId());
        afspraakRepository.save(nieuweAfspraak);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAfspraak(@PathVariable Long id){
        afspraakRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
