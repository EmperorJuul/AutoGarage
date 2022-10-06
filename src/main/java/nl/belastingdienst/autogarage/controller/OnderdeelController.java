package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.model.Onderdeel;
import nl.belastingdienst.autogarage.repository.OnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/onderdeel")
public class OnderdeelController {

    @Autowired
    private OnderdeelRepository onderdeelRepository;

    @GetMapping
    public ResponseEntity<List<Onderdeel>> alleOnderdelen(){
        List<Onderdeel> onderdeelList = new ArrayList<>();
        for(Onderdeel onderdeel : onderdeelRepository.findAll()){
            onderdeelList.add(onderdeel);
        }
        return ResponseEntity.ok(onderdeelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Onderdeel>> onderdeelOpId(@PathVariable Long id){
        return ResponseEntity.ok(onderdeelRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuwOnderdeel(@RequestBody Onderdeel onderdeel){
        onderdeelRepository.save(onderdeel);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOnderdeel(@PathVariable Long id, @RequestBody Onderdeel nieuwOnderdeel){
        Optional<Onderdeel> onderdeel = onderdeelRepository.findById(id);
        nieuwOnderdeel.setId(onderdeel.get().getId());
        onderdeelRepository.save(nieuwOnderdeel);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderOnderdeel(@PathVariable Long id){
        onderdeelRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
