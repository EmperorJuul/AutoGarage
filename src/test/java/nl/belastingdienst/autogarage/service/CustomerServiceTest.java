package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.KlantDto;
import nl.belastingdienst.autogarage.model.Customer;
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
class CustomerServiceTest {

    @Mock
    KlantRepository klantRepository;

    @InjectMocks
    KlantService klantService;

    @Mock
    Customer customer1;
    @Mock
    Customer customer2;

    @BeforeEach
    public void setup(){
        customer1 = new Customer("Juul", "Konings", "0612345678", "jk@outlook.com");
        customer1.setId(1L);
        customer2 = new Customer("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        customer2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void alleKlanten(){
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        KlantDto klantDto1 = new KlantDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        klantDto1.setId(1L);
        KlantDto klantDto2 = new KlantDto("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klantDto2.setId(2L);
        List<KlantDto> verwacht = new ArrayList<>();
        verwacht.add(klantDto1);
        verwacht.add(klantDto2);

        Mockito
                .when(klantRepository.findAll())
                .thenReturn(customerList);

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
                .when(klantRepository.findById(customer1.getId()))
                .thenReturn(Optional.of(customer1));

        KlantDto uitkomst = klantService.klantOpId(customer1.getId());

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
                .when(klantRepository.save(customer1))
                .thenReturn(customer1);

        KlantDto uitkomst = klantService.nieuweKlant(customer1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getVoornaam(), uitkomst.getVoornaam());
        assertEquals(verwacht.getAchternaam(), uitkomst.getAchternaam());
        assertEquals(verwacht.getEmailAdres(), uitkomst.getEmailAdres());
        assertEquals(verwacht.getTelefoonnummer(), uitkomst.getTelefoonnummer());
    }

    @Test
    void updateKlant(){
        Customer nieuweCustomer = new Customer("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        nieuweCustomer.setId(1L);

        Mockito
                .when(klantRepository.findById(customer1.getId()))
                .thenReturn(Optional.of(customer1));

        Mockito
                .when(klantRepository.save(nieuweCustomer))
                .thenReturn(nieuweCustomer);

        klantService.updateKlant(nieuweCustomer.getId(), nieuweCustomer);

        Mockito.verify(klantRepository, Mockito.times(1)).findById(customer1.getId());
        Mockito.verify(klantRepository, Mockito.times(1)).save(nieuweCustomer);
    }

    @Test
    void verwijderAfsrpaak(){
        klantService.verwijderKlant(customer1.getId());

        Mockito.verify(klantRepository, Mockito.times(1)).deleteById(customer1.getId());
    }

}