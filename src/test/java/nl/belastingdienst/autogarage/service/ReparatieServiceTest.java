package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.ReparatieDto;
import nl.belastingdienst.autogarage.model.Reparatie;
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
    Reparatie reparatie1;
    @Mock
    Reparatie reparatie2;

    @BeforeEach
    public void setup(){
        reparatie1 = new Reparatie("Banden vervangen", 104);
        reparatie1.setId(1L);
        reparatie2 = new Reparatie("Olie vervangen", 90);
        reparatie2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleReparaties(){
        List<Reparatie> reparatieList = new ArrayList<>();
        reparatieList.add(reparatie1);
        reparatieList.add(reparatie2);

        ReparatieDto reparatieDto1 = new ReparatieDto("Banden vervangen", 104);
        reparatieDto1.setId(1L);
        ReparatieDto reparatieDto2 = new ReparatieDto("Olie vervangen", 90);
        reparatieDto2.setId(2L);
        List<ReparatieDto> verwacht = new ArrayList<>();
        verwacht.add(reparatieDto1);
        verwacht.add(reparatieDto2);

        Mockito
                .when(reparatieRepository.findAll())
                .thenReturn(reparatieList);

        List<ReparatieDto> uitkomst = reparatieService.alleReparaties();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getNaam(), uitkomst.get(0).getNaam());
        assertEquals(verwacht.get(0).getPrijs(), uitkomst.get(0).getPrijs());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getNaam(), uitkomst.get(1).getNaam());
        assertEquals(verwacht.get(1).getPrijs(), uitkomst.get(1).getPrijs());
    }

    @Test
    void reparatieOpId(){
        ReparatieDto verwacht = new ReparatieDto("Banden vervangen", 104);
        verwacht.setId(1L);

        Mockito
                .when(reparatieRepository.findById(reparatie1.getId()))
                .thenReturn(Optional.of(reparatie1));

        ReparatieDto uitkomst = reparatieService.reparatieOpId(reparatie1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getNaam(), uitkomst.getNaam());
        assertEquals(verwacht.getPrijs(), uitkomst.getPrijs());
    }

    @Test
    void nieuweReparatie(){
        ReparatieDto verwacht = new ReparatieDto("Banden vervangen", 104);
        verwacht.setId(1L);

        Mockito
                .when(reparatieRepository.save(reparatie1))
                .thenReturn(reparatie1);

        ReparatieDto uitkomst = reparatieService.nieuweReparatie(reparatie1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getNaam(), uitkomst.getNaam());
        assertEquals(verwacht.getPrijs(), uitkomst.getPrijs());
    }

    @Test
    void updateReparatie(){
        Reparatie nieuweReparatie = new Reparatie("Olie vervangen", 90);
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