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
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> allCars(){
        return ResponseEntity.ok(carService.allCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> carById(@PathVariable Long id){
        return ResponseEntity.ok(carService.carById(id));
    }

    @PostMapping
    public ResponseEntity<Object> newCar(@RequestBody Car car){
        CarDto carDto = carService.newCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id, @RequestBody Car newCar){
        carService.updateCar(id, newCar);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
