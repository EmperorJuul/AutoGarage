package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AutoDto;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.repository.AutoRepository;
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
    AutoRepository autoRepository;

    @InjectMocks
    AutoService autoService;

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

        AutoDto autoDto1 = new AutoDto("Opel", "Corsa", 2006, "DF-45-A4");
        autoDto1.setId(1L);
        AutoDto autoDto2 = new AutoDto("Volkswagen", "ID4", 2021, "23-HC-6G");
        autoDto2.setId(2L);
        List<AutoDto> verwacht = new ArrayList<>();
        verwacht.add(autoDto1);
        verwacht.add(autoDto2);

        Mockito
                .when(autoRepository.findAll())
                .thenReturn(carList);

        List<AutoDto> uitkomst = autoService.alleAutos();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getBouwjaar(), uitkomst.get(0).getBouwjaar());
        assertEquals(verwacht.get(0).getModel(), uitkomst.get(0).getModel());
        assertEquals(verwacht.get(0).getMerk(), uitkomst.get(0).getMerk());
        assertEquals(verwacht.get(0).getKenteken(), uitkomst.get(0).getKenteken());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getBouwjaar(), uitkomst.get(1).getBouwjaar());
        assertEquals(verwacht.get(1).getModel(), uitkomst.get(1).getModel());
        assertEquals(verwacht.get(1).getMerk(), uitkomst.get(1).getMerk());
        assertEquals(verwacht.get(1).getKenteken(), uitkomst.get(1).getKenteken());
    }

    @Test
    void AutoOpId(){
        AutoDto verwacht = new AutoDto("Opel", "Corsa", 2006, "DF-45-A4");
        verwacht.setId(1L);

        Mockito
                .when(autoRepository.findById(car1.getId()))
                .thenReturn(Optional.of(car1));

        AutoDto uitkomst = autoService.autoOpId(car1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getMerk(), uitkomst.getMerk());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getBouwjaar(), uitkomst.getBouwjaar());
        assertEquals(verwacht.getKenteken(), uitkomst.getKenteken());
    }

    @Test
    void nieuweAuto(){
        AutoDto verwacht = new AutoDto("Opel", "Corsa", 2006, "DF-45-A4");
        verwacht.setId(1L);

        Mockito
                .when(autoRepository.save(car1))
                .thenReturn(car1);

        AutoDto uitkomst = autoService.nieuweAuto(car1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getMerk(), uitkomst.getMerk());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getBouwjaar(), uitkomst.getBouwjaar());
        assertEquals(verwacht.getKenteken(), uitkomst.getKenteken());

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

        autoService.updateAuto(nieuweCar.getId(), nieuweCar);

        Mockito.verify(autoRepository, Mockito.times(1)).findById(car1.getId());
        Mockito.verify(autoRepository, Mockito.times(1)).save(nieuweCar);
    }

    @Test
    void verwijderAuto(){
        autoService.verwijderAuto(car1.getId());

        Mockito.verify(autoRepository, Mockito.times(1)).deleteById(car1.getId());
    }

}