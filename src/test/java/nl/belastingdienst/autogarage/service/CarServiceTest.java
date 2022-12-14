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
    CarRepository autoRepository;

    @InjectMocks
    CarService autoService;

    @Mock
    Car car1;
    @Mock
    Car car2;

    @BeforeEach
    public void setup(){
        car1 = new Car("Opel", "Corsa", 2006, "DF-45-A4");
        car1.setId(1L);
        car2 = new Car("Volkswagen", "ID4", 2021, "23-HC-6G");
        car2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleAutos(){
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        CarDto autoDto1 = new CarDto("Opel", "Corsa", 2006, "DF-45-A4");
        autoDto1.setId(1L);
        CarDto autoDto2 = new CarDto("Volkswagen", "ID4", 2021, "23-HC-6G");
        autoDto2.setId(2L);
        List<CarDto> verwacht = new ArrayList<>();
        verwacht.add(autoDto1);
        verwacht.add(autoDto2);

        Mockito
                .when(autoRepository.findAll())
                .thenReturn(carList);

        List<CarDto> uitkomst = autoService.allCars();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getYear(), uitkomst.get(0).getYear());
        assertEquals(verwacht.get(0).getModel(), uitkomst.get(0).getModel());
        assertEquals(verwacht.get(0).getBrand(), uitkomst.get(0).getBrand());
        assertEquals(verwacht.get(0).getLicensePlate(), uitkomst.get(0).getLicensePlate());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getYear(), uitkomst.get(1).getYear());
        assertEquals(verwacht.get(1).getModel(), uitkomst.get(1).getModel());
        assertEquals(verwacht.get(1).getBrand(), uitkomst.get(1).getBrand());
        assertEquals(verwacht.get(1).getLicensePlate(), uitkomst.get(1).getLicensePlate());
    }

    @Test
    void AutoOpId(){
        CarDto verwacht = new CarDto("Opel", "Corsa", 2006, "DF-45-A4");
        verwacht.setId(1L);

        Mockito
                .when(autoRepository.findById(car1.getId()))
                .thenReturn(Optional.of(car1));

        CarDto uitkomst = autoService.carById(car1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getYear(), uitkomst.getYear());
        assertEquals(verwacht.getLicensePlate(), uitkomst.getLicensePlate());
    }

    @Test
    void nieuweAuto(){
        CarDto verwacht = new CarDto("Opel", "Corsa", 2006, "DF-45-A4");
        verwacht.setId(1L);

        Mockito
                .when(autoRepository.save(car1))
                .thenReturn(car1);

        CarDto uitkomst = autoService.newCar(car1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getYear(), uitkomst.getYear());
        assertEquals(verwacht.getLicensePlate(), uitkomst.getLicensePlate());

    }

    @Test
    void updateAuto(){
        Car nieuweCar = new Car("Suzuki", "Swift", 2012, "AA-AA-AA");
        nieuweCar.setId(1L);

        Mockito
                .when(autoRepository.findById(car1.getId()))
                .thenReturn(Optional.of(car1));

        Mockito
                .when(autoRepository.save(nieuweCar))
                .thenReturn(nieuweCar);

        autoService.updateCar(nieuweCar.getId(), nieuweCar);

        Mockito.verify(autoRepository, Mockito.times(1)).findById(car1.getId());
        Mockito.verify(autoRepository, Mockito.times(1)).save(nieuweCar);
    }

    @Test
    void verwijderAuto(){
        autoService.deleteCar(car1.getId());

        Mockito.verify(autoRepository, Mockito.times(1)).deleteById(car1.getId());
    }

}