package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.KlantDto;
import nl.belastingdienst.autogarage.model.Klant;
import nl.belastingdienst.autogarage.repository.KlantRepository;
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
class KlantServiceTest {

    @Mock
    KlantRepository klantRepository;

    @InjectMocks
    KlantService klantService;

    @Mock
    Klant klant1;
    @Mock
    Klant klant2;

    @BeforeEach
    public void setup(){
        klant1 = new Klant("Juul", "Konings", "0612345678", "jk@outlook.com");
        klant1.setId(1L);
        klant2 = new Klant("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klant2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleKlanten(){
        List<Klant> klantList = new ArrayList<>();
        klantList.add(klant1);
        klantList.add(klant2);

        KlantDto klantDto1 = new KlantDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        klantDto1.setId(1L);
        KlantDto klantDto2 = new KlantDto("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klantDto2.setId(2L);
        List<KlantDto> verwacht = new ArrayList<>();
        verwacht.add(klantDto1);
        verwacht.add(klantDto2);

        Mockito
                .when(klantRepository.findAll())
                .thenReturn(klantList);

        List<KlantDto> uitkomst = klantService.alleKlanten();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getVoornaam(), uitkomst.get(0).getVoornaam());
        assertEquals(verwacht.get(0).getAchternaam(), uitkomst.get(0).getAchternaam());
        assertEquals(verwacht.get(0).getTelefoonnummer(), uitkomst.get(0).getTelefoonnummer());
        assertEquals(verwacht.get(0).getEmailAdres(), uitkomst.get(0).getEmailAdres());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getVoornaam(), uitkomst.get(1).getVoornaam());
        assertEquals(verwacht.get(1).getAchternaam(), uitkomst.get(1).getAchternaam());
        assertEquals(verwacht.get(1).getTelefoonnummer(), uitkomst.get(1).getTelefoonnummer());
        assertEquals(verwacht.get(1).getEmailAdres(), uitkomst.get(1).getEmailAdres());
    }

    @Test
    void KlantOpId(){
        KlantDto verwacht = new KlantDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        verwacht.setId(1L);

        Mockito
                .when(klantRepository.findById(klant1.getId()))
                .thenReturn(Optional.of(klant1));

        KlantDto uitkomst = klantService.klantOpId(klant1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getVoornaam(), uitkomst.getVoornaam());
        assertEquals(verwacht.getAchternaam(), uitkomst.getAchternaam());
        assertEquals(verwacht.getEmailAdres(), uitkomst.getEmailAdres());
        assertEquals(verwacht.getTelefoonnummer(), uitkomst.getTelefoonnummer());
    }

    @Test
    void nieuweKlant(){
        KlantDto verwacht = new KlantDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        verwacht.setId(1L);

        Mockito
                .when(klantRepository.save(klant1))
                .thenReturn(klant1);

        KlantDto uitkomst = klantService.nieuweKlant(klant1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getVoornaam(), uitkomst.getVoornaam());
        assertEquals(verwacht.getAchternaam(), uitkomst.getAchternaam());
        assertEquals(verwacht.getEmailAdres(), uitkomst.getEmailAdres());
        assertEquals(verwacht.getTelefoonnummer(), uitkomst.getTelefoonnummer());
    }

    @Test
    void updateKlant(){
        Klant nieuweKlant = new Klant("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        nieuweKlant.setId(1L);

        Mockito
                .when(klantRepository.findById(klant1.getId()))
                .thenReturn(Optional.of(klant1));

        Mockito
                .when(klantRepository.save(nieuweKlant))
                .thenReturn(nieuweKlant);

        klantService.updateKlant(nieuweKlant.getId(), nieuweKlant);

        Mockito.verify(klantRepository, Mockito.times(1)).findById(klant1.getId());
        Mockito.verify(klantRepository, Mockito.times(1)).save(nieuweKlant);
    }

    @Test
    void verwijderAfsrpaak(){
        klantService.verwijderKlant(klant1.getId());

        Mockito.verify(klantRepository, Mockito.times(1)).deleteById(klant1.getId());
    }

}