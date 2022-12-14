package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.CarDto;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.service.CarService;
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
    private CarService autoService;

    @GetMapping
    public ResponseEntity<List<CarDto>> alleAutos(){
        return ResponseEntity.ok(autoService.allCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> AutoOpId(@PathVariable Long id){
        return ResponseEntity.ok(autoService.carById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAuto(@RequestBody Car car){
        CarDto autoDto = autoService.newCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autoDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuto(@PathVariable Long id, @RequestBody Car nieuweCar){
        autoService.updateCar(id, nieuweCar);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAuto(@PathVariable Long id){
        autoService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
