package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.CarDto;
import nl.belastingdienst.autogarage.exception.AutoNotFoundException;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public List<CarDto> alleAutos(){
        List<Car> carList = autoRepository.findAll();
        List<CarDto> autoDtoList = new ArrayList<>();
        for(Car car : carList){
            autoDtoList.add(vanAutoNaarAutoDto(car));
        }
        return autoDtoList;
    }

    public CarDto autoOpId(Long id){
        return vanAutoNaarAutoDto(autoRepository.findById(id).orElseThrow(() -> new AutoNotFoundException(id)));
    }

    public CarDto nieuweAuto(Car car){
        autoRepository.save(car);
        return vanAutoNaarAutoDto(car);
    }

    public void updateAuto(Long id, Car nieuweCar){
        Car car = autoRepository.findById(id).orElseThrow(() -> new AutoNotFoundException(id));
        nieuweCar.setId(car.getId());
        autoRepository.save(nieuweCar);
    }

    public void verwijderAuto(Long id){
        autoRepository.deleteById(id);
    }

    private CarDto vanAutoNaarAutoDto(Car car){
        CarDto autoDto = new CarDto(car.getBrand(), car.getModel(), car.getYear(), car.getLicenseplate());
        autoDto.setId(car.getId());
        return autoDto;
    }
}
