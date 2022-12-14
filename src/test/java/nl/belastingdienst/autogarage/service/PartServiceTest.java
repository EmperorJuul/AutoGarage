package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.PartDto;
import nl.belastingdienst.autogarage.model.Part;
import nl.belastingdienst.autogarage.repository.OnderdeelRepository;
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
    OnderdeelRepository onderdeelRepository;

    @InjectMocks
    OnderdeelService onderdeelService;

    @Mock
    Part part1;
    @Mock
    Part part2;

    @BeforeEach
    public void setup(){
        part1 = new Part("Band", "Michelin");
        part1.setId(1L);
        part2 = new Part("Ruit", "Noordglas");
        part2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleOnderdelen(){
        List<Part> partList = new ArrayList<>();
        partList.add(part1);
        partList.add(part2);

        PartDto onderdeelDto1 = new PartDto("Band", "Michelin");
        onderdeelDto1.setId(1L);
        PartDto onderdeelDto2 = new PartDto("Ruit", "Noordglas");
        onderdeelDto2.setId(2L);
        List<PartDto> verwacht = new ArrayList<>();
        verwacht.add(onderdeelDto1);
        verwacht.add(onderdeelDto2);

        Mockito
                .when(onderdeelRepository.findAll())
                .thenReturn(partList);

        List<PartDto> uitkomst = onderdeelService.alleOnderdelen();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getName(), uitkomst.get(0).getName());
        assertEquals(verwacht.get(0).getBrand(), uitkomst.get(0).getBrand());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getName(), uitkomst.get(1).getName());
        assertEquals(verwacht.get(1).getBrand(), uitkomst.get(1).getBrand());
    }

    @Test
    void onderdeelOpId(){
        PartDto verwacht = new PartDto("Band", "Michelin");
        verwacht.setId(1L);

        Mockito
                .when(onderdeelRepository.findById(part1.getId()))
                .thenReturn(Optional.of(part1));

        PartDto uitkomst = onderdeelService.onderdeelOpId(part1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
    }

    @Test
    void nieuwOnderdeel(){
        PartDto verwacht = new PartDto("Band", "Michelin");
        verwacht.setId(1L);

        Mockito
                .when(onderdeelRepository.save(part1))
                .thenReturn(part1);

        PartDto uitkomst = onderdeelService.nieuwOnderdeel(part1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getName(), uitkomst.getName());
        assertEquals(verwacht.getBrand(), uitkomst.getBrand());
    }

    @Test
    void updateOnderdeel(){
        Part nieuwPart = new Part("Ruit", "Noordglas");
        nieuwPart.setId(1L);

        Mockito
                .when(onderdeelRepository.findById(part1.getId()))
                .thenReturn(Optional.of(part1));

        Mockito
                .when(onderdeelRepository.save(nieuwPart))
                .thenReturn(nieuwPart);

        onderdeelService.updateOnderdeel(nieuwPart.getId(), nieuwPart);

        Mockito.verify(onderdeelRepository, Mockito.times(1)).findById(part1.getId());
        Mockito.verify(onderdeelRepository, Mockito.times(1)).save(nieuwPart);
    }

    @Test
    void verwijderOnderdeel(){
        onderdeelService.verwijderOnderdeel(part1.getId());

        Mockito.verify(onderdeelRepository, Mockito.times(1)).deleteById(part1.getId());
    }

}