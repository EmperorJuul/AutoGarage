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
    void allCars(){
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        List<CarDto> expected = new ArrayList<>();
        expected.add(carDto1);
        expected.add(carDto2);

        Mockito
                .when(carRepository.findAll())
                .thenReturn(carList);

        List<CarDto> actual = carService.allCars();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getYear(), actual.get(0).getYear());
        assertEquals(expected.get(0).getModel(), actual.get(0).getModel());
        assertEquals(expected.get(0).getBrand(), actual.get(0).getBrand());
        assertEquals(expected.get(0).getLicenseplate(), actual.get(0).getLicenseplate());

        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(1).getYear(), actual.get(1).getYear());
        assertEquals(expected.get(1).getModel(), actual.get(1).getModel());
        assertEquals(expected.get(1).getBrand(), actual.get(1).getBrand());
        assertEquals(expected.get(1).getLicenseplate(), actual.get(1).getLicenseplate());
    }

    @Test
    void carById(){
        CarDto expected = carDto1;

        Mockito
                .when(carRepository.findById(car1.getId()))
                .thenReturn(Optional.of(car1));

        CarDto actual = carService.carById(car1.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getYear(), actual.getYear());
        assertEquals(expected.getLicenseplate(), actual.getLicenseplate());
    }

    @Test
    void newCar(){
        CarDto expected = carDto1;

        Mockito
                .when(carRepository.save(Mockito.any()))
                .thenReturn(car1);

        CarDto actual = carService.newCar(carDto1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getYear(), actual.getYear());
        assertEquals(expected.getLicenseplate(), actual.getLicenseplate());

    }

    @Test
    void updateCar(){
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
    void deleteCar(){
        carService.deleteCar(car1.getId());

        Mockito.verify(carRepository, Mockito.times(1)).deleteById(car1.getId());
    }

}