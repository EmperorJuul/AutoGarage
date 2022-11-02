package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.OnderdeelDto;
import nl.belastingdienst.autogarage.model.Onderdeel;
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
class OnderdeelServiceTest {

    @Mock
    OnderdeelRepository onderdeelRepository;

    @InjectMocks
    OnderdeelService onderdeelService;

    @Mock
    Onderdeel onderdeel1;
    @Mock
    Onderdeel onderdeel2;

    @BeforeEach
    public void setup(){
        onderdeel1 = new Onderdeel("Band", "Michelin");
        onderdeel1.setId(1L);
        onderdeel2 = new Onderdeel("Ruit", "Noordglas");
        onderdeel2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleOnderdelen(){
        List<Onderdeel> onderdeelList = new ArrayList<>();
        onderdeelList.add(onderdeel1);
        onderdeelList.add(onderdeel2);

        OnderdeelDto onderdeelDto1 = new OnderdeelDto("Band", "Michelin");
        onderdeelDto1.setId(1L);
        OnderdeelDto onderdeelDto2 = new OnderdeelDto("Ruit", "Noordglas");
        onderdeelDto2.setId(2L);
        List<OnderdeelDto> verwacht = new ArrayList<>();
        verwacht.add(onderdeelDto1);
        verwacht.add(onderdeelDto2);

        Mockito
                .when(onderdeelRepository.findAll())
                .thenReturn(onderdeelList);

        List<OnderdeelDto> uitkomst = onderdeelService.alleOnderdelen();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getNaam(), uitkomst.get(0).getNaam());
        assertEquals(verwacht.get(0).getMerk(), uitkomst.get(0).getMerk());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getNaam(), uitkomst.get(1).getNaam());
        assertEquals(verwacht.get(1).getMerk(), uitkomst.get(1).getMerk());
    }

    @Test
    void onderdeelOpId(){
        OnderdeelDto verwacht = new OnderdeelDto("Band", "Michelin");
        verwacht.setId(1L);

        Mockito
                .when(onderdeelRepository.findById(onderdeel1.getId()))
                .thenReturn(Optional.of(onderdeel1));

        OnderdeelDto uitkomst = onderdeelService.onderdeelOpId(onderdeel1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getNaam(), uitkomst.getNaam());
        assertEquals(verwacht.getMerk(), uitkomst.getMerk());
    }

    @Test
    void nieuwOnderdeel(){
        OnderdeelDto verwacht = new OnderdeelDto("Band", "Michelin");
        verwacht.setId(1L);

        Mockito
                .when(onderdeelRepository.save(onderdeel1))
                .thenReturn(onderdeel1);

        OnderdeelDto uitkomst = onderdeelService.nieuwOnderdeel(onderdeel1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getNaam(), uitkomst.getNaam());
        assertEquals(verwacht.getMerk(), uitkomst.getMerk());
    }

    @Test
    void updateOnderdeel(){
        Onderdeel nieuwOnderdeel = new Onderdeel("Ruit", "Noordglas");
        nieuwOnderdeel.setId(1L);

        Mockito
                .when(onderdeelRepository.findById(onderdeel1.getId()))
                .thenReturn(Optional.of(onderdeel1));

        Mockito
                .when(onderdeelRepository.save(nieuwOnderdeel))
                .thenReturn(nieuwOnderdeel);

        onderdeelService.updateOnderdeel(nieuwOnderdeel.getId(), nieuwOnderdeel);

        Mockito.verify(onderdeelRepository, Mockito.times(1)).findById(onderdeel1.getId());
        Mockito.verify(onderdeelRepository, Mockito.times(1)).save(nieuwOnderdeel);
    }

    @Test
    void verwijderOnderdeel(){
        onderdeelService.verwijderOnderdeel(onderdeel1.getId());

        Mockito.verify(onderdeelRepository, Mockito.times(1)).deleteById(onderdeel1.getId());
    }

}