package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AutoDto;
import nl.belastingdienst.autogarage.model.Auto;
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
class AutoServiceTest {

    @Mock
    AutoRepository autoRepository;

    @InjectMocks
    AutoService autoService;

    @Mock
    Auto auto1;
    @Mock
    Auto auto2;

    @BeforeEach
    public void setup(){
        auto1 = new Auto("Opel", "Corsa", 2006, "DF-45-A4");
        auto1.setId(1L);
        auto2 = new Auto("Volkswagen", "ID4", 2021, "23-HC-6G");
        auto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleAutos(){
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto1);
        autoList.add(auto2);

        AutoDto autoDto1 = new AutoDto("Opel", "Corsa", 2006, "DF-45-A4");
        autoDto1.setId(1L);
        AutoDto autoDto2 = new AutoDto("Volkswagen", "ID4", 2021, "23-HC-6G");
        autoDto2.setId(2L);
        List<AutoDto> verwacht = new ArrayList<>();
        verwacht.add(autoDto1);
        verwacht.add(autoDto2);

        Mockito
                .when(autoRepository.findAll())
                .thenReturn(autoList);

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
                .when(autoRepository.findById(auto1.getId()))
                .thenReturn(Optional.of(auto1));

        AutoDto uitkomst = autoService.autoOpId(auto1.getId());

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
                .when(autoRepository.save(auto1))
                .thenReturn(auto1);

        AutoDto uitkomst = autoService.nieuweAuto(auto1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getMerk(), uitkomst.getMerk());
        assertEquals(verwacht.getModel(), uitkomst.getModel());
        assertEquals(verwacht.getBouwjaar(), uitkomst.getBouwjaar());
        assertEquals(verwacht.getKenteken(), uitkomst.getKenteken());

    }

    @Test
    void updateAuto(){
        Auto nieuweAuto = new Auto("Suzuki", "Swift", 2012, "AA-AA-AA");
        nieuweAuto.setId(1L);

        Mockito
                .when(autoRepository.findById(auto1.getId()))
                .thenReturn(Optional.of(auto1));

        Mockito
                .when(autoRepository.save(nieuweAuto))
                .thenReturn(nieuweAuto);

        autoService.updateAuto(nieuweAuto.getId(), nieuweAuto);

        Mockito.verify(autoRepository, Mockito.times(1)).findById(auto1.getId());
        Mockito.verify(autoRepository, Mockito.times(1)).save(nieuweAuto);
    }

    @Test
    void verwijderAuto(){
        autoService.verwijderAuto(auto1.getId());

        Mockito.verify(autoRepository, Mockito.times(1)).deleteById(auto1.getId());
    }

}