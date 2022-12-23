package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.PartDto;
import nl.belastingdienst.autogarage.model.Part;
import nl.belastingdienst.autogarage.repository.PartRepository;
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
class PartServiceTest {

    @Mock
    PartRepository partRepository;

    @InjectMocks
    PartService partService;

    @Mock
    Part part1;
    @Mock
    Part part2;
    @Mock
    PartDto partDto1;
    @Mock
    PartDto partDto2;

    @BeforeEach
    public void setup(){
        part1 = new Part("Tire", "Michelin");
        part1.setId(1L);
        part2 = new Part("Window", "Noordglass");
        part2.setId(2L);
        partDto1 = new PartDto("Tire", "Michelin");
        partDto1.setId(1L);
        partDto2 = new PartDto("Window", "Noordglass");
        partDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void allParts(){
        List<Part> partList = new ArrayList<>();
        partList.add(part1);
        partList.add(part2);
        List<PartDto> expected = new ArrayList<>();
        expected.add(partDto1);
        expected.add(partDto2);

        Mockito
                .when(partRepository.findAll())
                .thenReturn(partList);

        List<PartDto> actual = partService.allParts();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getBrand(), actual.get(0).getBrand());

        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(1).getName(), actual.get(1).getName());
        assertEquals(expected.get(1).getBrand(), actual.get(1).getBrand());
    }

    @Test
    void partById(){
        PartDto expected = partDto1;

        Mockito
                .when(partRepository.findById(part1.getId()))
                .thenReturn(Optional.of(part1));

        PartDto actual = partService.partById(part1.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getBrand(), actual.getBrand());
    }

    @Test
    void newPart(){
        PartDto expected = partDto1;

        Mockito
                .when(partRepository.save(Mockito.any()))
                .thenReturn(part1);

        PartDto actual = partService.newPart(partDto1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getBrand(), actual.getBrand());
    }

    @Test
    void updatePart(){
        PartDto newPartDto = partDto2;

        Mockito
                .when(partRepository.findById(part1.getId()))
                .thenReturn(Optional.of(part1));

        Mockito
                .when(partRepository.save(part1))
                .thenReturn(part1);

        partService.updatePart(part1.getId(), newPartDto);

        Mockito.verify(partRepository, Mockito.times(1)).findById(part1.getId());
        Mockito.verify(partRepository, Mockito.times(1)).save(part1);
    }

    @Test
    void deletePart(){
        partService.deletePart(part1.getId());

        Mockito.verify(partRepository, Mockito.times(1)).deleteById(part1.getId());
    }

}