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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AfspraakServiceTest {

    @Mock
    AfspraakRepository afspraakRepository;

    @InjectMocks
    AfspraakService afspraakService;

    @Mock
    Afspraak afspraak;

    @BeforeEach
    public void setup(){
        afspraak = new Afspraak(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        afspraak.setId(1L);     //Id wordt in het programma geregeld door springboot
                                //om de test te laten slagen worden ze hier handmatig overschreven
    }


    @Test
    void afspraakOpId(){
        AfspraakDto verwacht = new AfspraakDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        verwacht.setId(1L);

        Mockito
                .when(afspraakRepository.findById(afspraak.getId()))
                .thenReturn(Optional.of(afspraak));

        AfspraakDto uitkomst = afspraakService.afspraakOpId(afspraak.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBeginAfsrpaak(), uitkomst.getBeginAfsrpaak());
        assertEquals(verwacht.getEindeAfspraak(), uitkomst.getEindeAfspraak());

    }


    @Test
    void nieuweAfspraak(){
        AfspraakDto verwacht = new AfspraakDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        verwacht.setId(1L);

        Mockito
                .when(afspraakRepository.save(afspraak))
                .thenReturn(afspraak);

        AfspraakDto uitkomst = afspraakService.nieuweAfspraak(afspraak);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getBeginAfsrpaak(), uitkomst.getBeginAfsrpaak());
        assertEquals(verwacht.getEindeAfspraak(), uitkomst.getEindeAfspraak());
    }


    @Test
    void updateAfspraak(){
        Afspraak nieuweAfspraak = new Afspraak(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        nieuweAfspraak.setId(1L);

        Mockito
                .when(afspraakRepository.findById(afspraak.getId()))
                .thenReturn(Optional.of(afspraak));

        Mockito
                .when(afspraakRepository.save(nieuweAfspraak))
                .thenReturn(nieuweAfspraak);

        afspraakService.updateAfspraak(nieuweAfspraak.getId(), nieuweAfspraak);

        Mockito.verify(afspraakRepository, Mockito.times(1)).findById(afspraak.getId());
        Mockito.verify(afspraakRepository, Mockito.times(1)).save(nieuweAfspraak);
    }


    @Test
    void verwijderAfspraak(){
        afspraakService.verwijderAfspraak(afspraak.getId());

        Mockito.verify(afspraakRepository, Mockito.times(1)).deleteById(afspraak.getId());
    }



}