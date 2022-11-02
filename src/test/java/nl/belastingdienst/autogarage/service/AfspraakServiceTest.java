package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AfspraakDto;
import nl.belastingdienst.autogarage.model.Afspraak;
import nl.belastingdienst.autogarage.repository.AfspraakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AfspraakServiceTest {

    @Mock
    AfspraakRepository afspraakRepository;

    @InjectMocks
    AfspraakService afspraakService;

    @Mock
    Afspraak afspraak1;
    @Mock
    Afspraak afspraak2;

    @BeforeEach
    public void setup(){
        afspraak1 = new Afspraak(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        afspraak1.setId(1L);     //Id wordt in het programma geregeld door springboot
                                //om de test te laten slagen worden ze hier handmatig overschreven
        afspraak2 = new Afspraak(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        afspraak2.setId(2L);
    }


    @Test
    void alleAfspraken(){
        List<Afspraak> afspraakList = new ArrayList<>();
        afspraakList.add(afspraak1);
        afspraakList.add(afspraak2);

        AfspraakDto afspraakDto1 = new AfspraakDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        afspraakDto1.setId(1L);
        AfspraakDto afspraakDto2 = new AfspraakDto(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        afspraakDto2.setId(2L);
        List<AfspraakDto> verwacht = new ArrayList<>();
        verwacht.add(afspraakDto1);
        verwacht.add(afspraakDto2);
        Mockito
                .when(afspraakRepository.findAll())
                .thenReturn(afspraakList);

        List<AfspraakDto> uitkomst =  afspraakService.alleAfspraken();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getBeginAfsrpaak(), uitkomst.get(0).getBeginAfsrpaak());
        assertEquals(verwacht.get(0).getEindeAfspraak(), uitkomst.get(0).getEindeAfspraak());
    }

    @Test
    void afspraakOpId(){
        AfspraakDto verwacht = new AfspraakDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        verwacht.setId(1L);

        Mockito
                .when(afspraakRepository.findById(afspraak1.getId()))
                .thenReturn(Optional.of(afspraak1));

        AfspraakDto uitkomst = afspraakService.afspraakOpId(afspraak1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBeginAfsrpaak(), uitkomst.getBeginAfsrpaak());
        assertEquals(verwacht.getEindeAfspraak(), uitkomst.getEindeAfspraak());

    }


    @Test
    void nieuweAfspraak(){
        AfspraakDto verwacht = new AfspraakDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        verwacht.setId(1L);

        Mockito
                .when(afspraakRepository.save(afspraak1))
                .thenReturn(afspraak1);

        AfspraakDto uitkomst = afspraakService.nieuweAfspraak(afspraak1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBeginAfsrpaak(), uitkomst.getBeginAfsrpaak());
        assertEquals(verwacht.getEindeAfspraak(), uitkomst.getEindeAfspraak());
    }


    @Test
    void updateAfspraak(){
        Afspraak nieuweAfspraak = new Afspraak(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        nieuweAfspraak.setId(1L);

        Mockito
                .when(afspraakRepository.findById(afspraak1.getId()))
                .thenReturn(Optional.of(afspraak1));

        Mockito
                .when(afspraakRepository.save(nieuweAfspraak))
                .thenReturn(nieuweAfspraak);

        afspraakService.updateAfspraak(nieuweAfspraak.getId(), nieuweAfspraak);

        Mockito.verify(afspraakRepository, Mockito.times(1)).findById(afspraak1.getId());
        Mockito.verify(afspraakRepository, Mockito.times(1)).save(nieuweAfspraak);
    }


    @Test
    void verwijderAfspraak(){
        afspraakService.verwijderAfspraak(afspraak1.getId());

        Mockito.verify(afspraakRepository, Mockito.times(1)).deleteById(afspraak1.getId());
    }



}