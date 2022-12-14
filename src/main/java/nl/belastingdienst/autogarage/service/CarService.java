package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.CarDto;
import nl.belastingdienst.autogarage.exception.CarNotFoundException;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDto> allCars(){
        List<Car> carList = carRepository.findAll();
        List<CarDto> carDtoList = new ArrayList<>();
        for(Car car : carList){
            carDtoList.add(fromCarToDto(car));
        }
        return carDtoList;
    }

    public CarDto carById(Long id){
        return fromCarToDto(carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public CarDto newCar(Car car){
        carRepository.save(car);
        return fromCarToDto(car);
    }

    public void updateCar(Long id, Car newCar){
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        newCar.setId(car.getId());
        carRepository.save(newCar);
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

    private CarDto fromCarToDto(Car car){
        CarDto carDto = new CarDto(car.getBrand(), car.getModel(), car.getYear(), car.getLicenseplate());
        carDto.setId(car.getId());
        return carDto;
    }
}