package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.model.Auto;
import nl.belastingdienst.autogarage.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping
    public ResponseEntity<List<Auto>> alleAutos(){
        List<Auto> autoList = new ArrayList<>();
        for(Auto auto : autoRepository.findAll()){
            autoList.add(auto);
        }
        return ResponseEntity.ok(autoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Auto>> AutoOpId(@PathVariable Long id){
        return ResponseEntity.ok(autoRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAuto(@RequestBody Auto auto){
        autoRepository.save(auto);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuto(@PathVariable Long id, @RequestBody Auto nieuweAuto){
        Optional<Auto> auto = autoRepository.findById(id);
        nieuweAuto.setId(auto.get().getId());
        autoRepository.save(nieuweAuto);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAuto(@PathVariable Long id){
        autoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
