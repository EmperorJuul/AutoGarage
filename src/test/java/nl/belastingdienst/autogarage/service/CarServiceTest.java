package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.CarDto;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Mock
    Car car1;
    @Mock
    Car car2;
    @Mock
    CarDto carDto1;
    @Mock
    CarDto carDto2;

    @BeforeEach
    public void setup(){
        car1 = new Car("Opel", "Corsa", 2006, "DF-45-A4");
        car1.setId(1L);
        car2 = new Car("Volkswagen", "ID4", 2021, "23-HC-6G");
        car2.setId(2L);
        carDto1 = new CarDto("Opel", "Corsa", 2006, "DF-45-A4");
        carDto1.setId(1L);
        carDto2 = new CarDto("Volkswagen", "ID4", 2021, "23-HC-6G");
        carDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleAutos(){
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        List<CarDto> verwacht = new ArrayList<>();
        verwacht.add(carDto1);
        verwacht.add(carDto2);

        Mockito
                .when(carRepository.findAll())
                .thenReturn(carList);

        List<CarDto> uitkomst = carService.allCars();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getYear(), uitkomst.get(0).getYear());
        assertEquals(verwacht.get(0).getModel(), uitkomst.get(0).getModel());
        assertEquals(verwacht.get(0).getBrand(), uitkomst.get(0).getBrand());
        assertEquals(verwacht.get(0).getLicenseplate(), uitkomst.get(0).getLicenseplate());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getYear(), uitkomst.get(1).getYear());
        assertEquals(verwacht.get(1).getModel(), uitkomst.get(1).getModel());
        assertEquals(verwacht.get(1).getBrand(), uitkomst.get(1).getBrand());
        assertEquals(verwacht.get(1).getLicenseplate(), uitkomst.get(1).getLicenseplate());
    }

    @Test
    void AutoOpId(){
        CarDto verwacht = carDto1;

        Mockito
                .when(carRepository.findById(car1.getId()))
                .thenReturn(Optional.of(car1));

        CarDto uitkomst = carService.carById(car1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getYear(), uitkomst.getYear());
        assertEquals(verwacht.getLicenseplate(), uitkomst.getLicenseplate());
    }

    @Test
    void nieuweAuto(){
        CarDto verwacht = carDto1;

        Mockito
                .when(carRepository.save(Mockito.any()))
                .thenReturn(car1);

        CarDto uitkomst = carService.newCar(carDto1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getYear(), uitkomst.getYear());
        assertEquals(verwacht.getLicenseplate(), uitkomst.getLicenseplate());

    }

    @Test
    void updateAuto(){
        CarDto newCarDto = carDto2;

        Mockito
                .when(carRepository.findById(car1.getId()))
                .thenReturn(Optional.of(car1));

        Mockito
                .when(carRepository.save(car1))
                .thenReturn(car1);

        carService.updateCar(car1.getId(), newCarDto);

        Mockito.verify(carRepository, Mockito.times(1)).findById(car1.getId());
        Mockito.verify(carRepository, Mockito.times(1)).save(car1);
    }

    @Test
    void verwijderAuto(){
        carService.deleteCar(car1.getId());

        Mockito.verify(carRepository, Mockito.times(1)).deleteById(car1.getId());
    }

}