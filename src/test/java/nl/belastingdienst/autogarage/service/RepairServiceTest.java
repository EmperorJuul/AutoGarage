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
class RepairServiceTest {

    @Mock
    RepairRepository repairRepository;

    @InjectMocks
    RepairService repairService;

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
        repair1 = new Repair("Replace tires", 104);
        repair1.setId(1L);
        repair2 = new Repair("Change oil", 90);
        repair2.setId(2L);
        repairDto1 = new RepairDto("Replace tires", 104);
        repairDto1.setId(1L);
        repairDto2 = new RepairDto("Change oil", 90);
        repairDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void allRepairs(){
        List<Repair> reparatieList = new ArrayList<>();
        reparatieList.add(repair1);
        reparatieList.add(repair2);
        List<RepairDto> expected = new ArrayList<>();
        expected.add(repairDto1);
        expected.add(repairDto2);

        Mockito
                .when(repairRepository.findAll())
                .thenReturn(reparatieList);

        List<RepairDto> actual = repairService.allRepairs();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());

        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(1).getName(), actual.get(1).getName());
        assertEquals(expected.get(1).getPrice(), actual.get(1).getPrice());
    }

    @Test
    void repairById(){
        RepairDto expected = repairDto1;

        Mockito
                .when(repairRepository.findById(repair1.getId()))
                .thenReturn(Optional.of(repair1));

        RepairDto actual = repairService.repairById(repair1.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    void newRepair(){
        RepairDto expected = repairDto1;

        Mockito
                .when(repairRepository.save(Mockito.any()))
                .thenReturn(repair1);

        RepairDto actual = repairService.newRepair(repairDto1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    void updateRepair(){
        RepairDto newRepairDto = repairDto2;

        Mockito
                .when(repairRepository.findById(repair1.getId()))
                .thenReturn(Optional.of(repair1));

        Mockito
                .when(repairRepository.save(repair1))
                .thenReturn(repair1);

        repairService.updateRepair(repair1.getId(), newRepairDto);

        Mockito.verify(repairRepository, Mockito.times(1)).findById(repair1.getId());
        Mockito.verify(repairRepository, Mockito.times(1)).save(repair1);
    }

    @Test
    void deleteRepair(){
        repairService.deleteRepair(repair1.getId());

        Mockito.verify(repairRepository, Mockito.times(1)).deleteById(repair1.getId());
    }

}