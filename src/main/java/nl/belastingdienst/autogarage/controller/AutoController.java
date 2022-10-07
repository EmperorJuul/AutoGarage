package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AutoDto;
import nl.belastingdienst.autogarage.model.Auto;
import nl.belastingdienst.autogarage.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public ResponseEntity<List<AutoDto>> alleAutos(){
        return ResponseEntity.ok(autoService.alleAutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoDto> AutoOpId(@PathVariable Long id){
        return ResponseEntity.ok(autoService.autoOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAuto(@RequestBody Auto auto){
        autoService.nieuweAuto(auto);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuto(@PathVariable Long id, @RequestBody Auto nieuweAuto){
        autoService.updateAuto(id, nieuweAuto);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAuto(@PathVariable Long id){
        autoService.verwijderAuto(id);
        return ResponseEntity.ok().build();
    }
}
