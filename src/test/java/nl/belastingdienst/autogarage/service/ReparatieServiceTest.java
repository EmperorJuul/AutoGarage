package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.RepairDto;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.repository.RepairRepository;
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
    RepairRepository reparatieRepository;

    @InjectMocks
    RepairService reparatieService;

    @Mock
    Repair repair1;
    @Mock
    Repair repair2;
    @Mock
    RepairDto repairDto1;
    @Mock
    RepairDto repairDto2;

    @BeforeEach
    public void setup(){
        repair1 = new Repair("Banden vervangen", 104);
        repair1.setId(1L);
        repair2 = new Repair("Olie vervangen", 90);
        repair2.setId(2L);
        repairDto1 = new RepairDto("Banden vervangen", 104);
        repairDto1.setId(1L);
        repairDto2 = new RepairDto("Olie vervangen", 90);
        repairDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleReparaties(){
        List<Repair> reparatieList = new ArrayList<>();
        reparatieList.add(repair1);
        reparatieList.add(repair2);
        List<RepairDto> verwacht = new ArrayList<>();
        verwacht.add(repairDto1);
        verwacht.add(repairDto2);

        Mockito
                .when(reparatieRepository.findAll())
                .thenReturn(reparatieList);

        List<RepairDto> uitkomst = reparatieService.allRepairs();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getName(), uitkomst.get(0).getName());
        assertEquals(verwacht.get(0).getPrice(), uitkomst.get(0).getPrice());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getName(), uitkomst.get(1).getName());
        assertEquals(verwacht.get(1).getPrice(), uitkomst.get(1).getPrice());
    }

    @Test
    void reparatieOpId(){
        RepairDto verwacht = repairDto1;

        Mockito
                .when(reparatieRepository.findById(repair1.getId()))
                .thenReturn(Optional.of(repair1));

        RepairDto uitkomst = reparatieService.repairById(repair1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getPrice(), uitkomst.getPrice());
    }

    @Test
    void nieuweReparatie(){
        RepairDto verwacht = repairDto1;

        Mockito
                .when(reparatieRepository.save(Mockito.any()))
                .thenReturn(repair1);

        RepairDto uitkomst = reparatieService.newRepair(repairDto1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getPrice(), uitkomst.getPrice());
    }

    @Test
    void updateReparatie(){
        RepairDto newRepairDto = repairDto2;

        Mockito
                .when(reparatieRepository.findById(repair1.getId()))
                .thenReturn(Optional.of(repair1));

        Mockito
                .when(reparatieRepository.save(repair1))
                .thenReturn(repair1);

        reparatieService.updateRepair(repair1.getId(), newRepairDto);

        Mockito.verify(reparatieRepository, Mockito.times(1)).findById(repair1.getId());
        Mockito.verify(reparatieRepository, Mockito.times(1)).save(repair1);
    }

    @Test
    void verwijderReparatie(){
        reparatieService.deleteRepair(repair1.getId());

        Mockito.verify(reparatieRepository, Mockito.times(1)).deleteById(repair1.getId());
    }

}