package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AutoDto;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Object> nieuweAuto(@RequestBody Car car){
        AutoDto autoDto = autoService.nieuweAuto(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autoDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuto(@PathVariable Long id, @RequestBody Car nieuweCar){
        autoService.updateAuto(id, nieuweCar);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAuto(@PathVariable Long id){
        autoService.verwijderAuto(id);
        return ResponseEntity.noContent().build();
    }
}
