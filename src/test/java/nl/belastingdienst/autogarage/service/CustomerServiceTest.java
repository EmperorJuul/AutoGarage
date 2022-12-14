package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.CustomerDto;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.repository.CustomerRepository;
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
    CustomerRepository klantRepository;

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

        CustomerDto klantDto1 = new CustomerDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        klantDto1.setId(1L);
        CustomerDto klantDto2 = new CustomerDto("Pieter", "Hogeboboom", "0687654321", "PH@hotmail.com");
        klantDto2.setId(2L);
        List<CustomerDto> verwacht = new ArrayList<>();
        verwacht.add(klantDto1);
        verwacht.add(klantDto2);

        Mockito
                .when(klantRepository.findAll())
                .thenReturn(customerList);

        List<CustomerDto> uitkomst = klantService.alleKlanten();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getFirstname(), uitkomst.get(0).getFirstname());
        assertEquals(verwacht.get(0).getSurname(), uitkomst.get(0).getSurname());
        assertEquals(verwacht.get(0).getPhoneNumber(), uitkomst.get(0).getPhoneNumber());
        assertEquals(verwacht.get(0).getEmailAdress(), uitkomst.get(0).getEmailAdress());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getFirstname(), uitkomst.get(1).getFirstname());
        assertEquals(verwacht.get(1).getSurname(), uitkomst.get(1).getSurname());
        assertEquals(verwacht.get(1).getPhoneNumber(), uitkomst.get(1).getPhoneNumber());
        assertEquals(verwacht.get(1).getEmailAdress(), uitkomst.get(1).getEmailAdress());
    }

    @Test
    void KlantOpId(){
        CustomerDto verwacht = new CustomerDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        verwacht.setId(1L);

        Mockito
                .when(klantRepository.findById(customer1.getId()))
                .thenReturn(Optional.of(customer1));

        CustomerDto uitkomst = klantService.klantOpId(customer1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getFirstname(), uitkomst.getFirstname());
        assertEquals(verwacht.getSurname(), uitkomst.getSurname());
        assertEquals(verwacht.getEmailAdress(), uitkomst.getEmailAdress());
        assertEquals(verwacht.getPhoneNumber(), uitkomst.getPhoneNumber());
    }

    @Test
    void nieuweKlant(){
        CustomerDto verwacht = new CustomerDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        verwacht.setId(1L);

        Mockito
                .when(klantRepository.save(customer1))
                .thenReturn(customer1);

        CustomerDto uitkomst = klantService.nieuweKlant(customer1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getFirstname(), uitkomst.getFirstname());
        assertEquals(verwacht.getSurname(), uitkomst.getSurname());
        assertEquals(verwacht.getEmailAdress(), uitkomst.getEmailAdress());
        assertEquals(verwacht.getPhoneNumber(), uitkomst.getPhoneNumber());
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