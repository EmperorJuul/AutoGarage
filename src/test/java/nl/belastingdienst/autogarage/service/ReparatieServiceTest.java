package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.RepairDto;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.repository.ReparatieRepository;
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
class ReparatieServiceTest {

    @Mock
    ReparatieRepository reparatieRepository;

    @InjectMocks
    ReparatieService reparatieService;

    @Mock
    Repair reparatie1;
    @Mock
    Repair reparatie2;

    @BeforeEach
    public void setup(){
        reparatie1 = new Repair("Banden vervangen", 104);
        reparatie1.setId(1L);
        reparatie2 = new Repair("Olie vervangen", 90);
        reparatie2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleReparaties(){
        List<Repair> reparatieList = new ArrayList<>();
        reparatieList.add(reparatie1);
        reparatieList.add(reparatie2);

        RepairDto reparatieDto1 = new RepairDto("Banden vervangen", 104);
        reparatieDto1.setId(1L);
        RepairDto reparatieDto2 = new RepairDto("Olie vervangen", 90);
        reparatieDto2.setId(2L);
        List<RepairDto> verwacht = new ArrayList<>();
        verwacht.add(reparatieDto1);
        verwacht.add(reparatieDto2);

        Mockito
                .when(reparatieRepository.findAll())
                .thenReturn(reparatieList);

        List<RepairDto> uitkomst = reparatieService.alleReparaties();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getName(), uitkomst.get(0).getName());
        assertEquals(verwacht.get(0).getPrice(), uitkomst.get(0).getPrice());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getName(), uitkomst.get(1).getName());
        assertEquals(verwacht.get(1).getPrice(), uitkomst.get(1).getPrice());
    }

    @Test
    void reparatieOpId(){
        RepairDto verwacht = new RepairDto("Banden vervangen", 104);
        verwacht.setId(1L);

        Mockito
                .when(reparatieRepository.findById(reparatie1.getId()))
                .thenReturn(Optional.of(reparatie1));

        RepairDto uitkomst = reparatieService.reparatieOpId(reparatie1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getPrice(), uitkomst.getPrice());
    }

    @Test
    void nieuweReparatie(){
        RepairDto verwacht = new RepairDto("Banden vervangen", 104);
        verwacht.setId(1L);

        Mockito
                .when(reparatieRepository.save(reparatie1))
                .thenReturn(reparatie1);

        RepairDto uitkomst = reparatieService.nieuweReparatie(reparatie1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getPrice(), uitkomst.getPrice());
    }

    @Test
    void updateReparatie(){
        Repair nieuweReparatie = new Repair("Olie vervangen", 90);
        nieuweReparatie.setId(1L);

        Mockito
                .when(reparatieRepository.findById(reparatie1.getId()))
                .thenReturn(Optional.of(reparatie1));

        Mockito
                .when(reparatieRepository.save(nieuweReparatie))
                .thenReturn(nieuweReparatie);

        reparatieService.updateReparatie(nieuweReparatie.getId(), nieuweReparatie);

        Mockito.verify(reparatieRepository, Mockito.times(1)).findById(reparatie1.getId());
        Mockito.verify(reparatieRepository, Mockito.times(1)).save(nieuweReparatie);
    }

    @Test
    void verwijderReparatie(){
        reparatieService.verwijderReparatie(reparatie1.getId());

        Mockito.verify(reparatieRepository, Mockito.times(1)).deleteById(reparatie1.getId());
    }

}