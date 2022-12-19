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
    PartRepository onderdeelRepository;

    @InjectMocks
    PartService onderdeelService;

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
        part1 = new Part("Band", "Michelin");
        part1.setId(1L);
        part2 = new Part("Ruit", "Noordglas");
        part2.setId(2L);
        partDto1 = new PartDto("Band", "Michelin");
        partDto1.setId(1L);
        partDto2 = new PartDto("Ruit", "Noordglas");
        partDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleOnderdelen(){
        List<Part> partList = new ArrayList<>();
        partList.add(part1);
        partList.add(part2);
        List<PartDto> verwacht = new ArrayList<>();
        verwacht.add(partDto1);
        verwacht.add(partDto2);

        Mockito
                .when(onderdeelRepository.findAll())
                .thenReturn(partList);

        List<PartDto> uitkomst = onderdeelService.allParts();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getName(), uitkomst.get(0).getName());
        assertEquals(verwacht.get(0).getBrand(), uitkomst.get(0).getBrand());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getName(), uitkomst.get(1).getName());
        assertEquals(verwacht.get(1).getBrand(), uitkomst.get(1).getBrand());
    }

    @Test
    void onderdeelOpId(){
        PartDto verwacht = partDto1;

        Mockito
                .when(onderdeelRepository.findById(part1.getId()))
                .thenReturn(Optional.of(part1));

        PartDto uitkomst = onderdeelService.partById(part1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
    }

    @Test
    void nieuwOnderdeel(){
        PartDto verwacht = partDto1;

        Mockito
                .when(onderdeelRepository.save(Mockito.any()))
                .thenReturn(part1);

        PartDto uitkomst = onderdeelService.newPart(partDto1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
    }

    @Test
    void updateOnderdeel(){
        PartDto newPartDto = partDto2;

        Mockito
                .when(onderdeelRepository.findById(part1.getId()))
                .thenReturn(Optional.of(part1));

        Mockito
                .when(onderdeelRepository.save(part1))
                .thenReturn(part1);

        onderdeelService.updatePart(part1.getId(), newPartDto);

        Mockito.verify(onderdeelRepository, Mockito.times(1)).findById(part1.getId());
        Mockito.verify(onderdeelRepository, Mockito.times(1)).save(part1);
    }

    @Test
    void verwijderOnderdeel(){
        onderdeelService.deletePart(part1.getId());

        Mockito.verify(onderdeelRepository, Mockito.times(1)).deleteById(part1.getId());
    }

}