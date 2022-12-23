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
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Mock
    Customer customer1;
    @Mock
    Customer customer2;
    @Mock
    CustomerDto customerDto1;
    @Mock
    CustomerDto customerDto2;

    @BeforeEach
    public void setup(){
        customer1 = new Customer("Juul", "Konings", "0612345678", "jk@outlook.com");
        customer1.setId(1L);
        customer2 = new Customer("John", "Smith", "0687654321", "JS@hotmail.com");
        customer2.setId(2L);
        customerDto1 = new CustomerDto("Juul", "Konings", "0612345678", "jk@outlook.com");
        customerDto1.setId(1L);
        customerDto2 = new CustomerDto("John", "Smith", "0687654321", "JS@hotmail.com");
        customerDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }

    @Test
    void allCustomers(){
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        List<CustomerDto> expected = new ArrayList<>();
        expected.add(customerDto1);
        expected.add(customerDto2);

        Mockito
                .when(customerRepository.findAll())
                .thenReturn(customerList);

        List<CustomerDto> actual = customerService.allCustomers();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getFirstname(), actual.get(0).getFirstname());
        assertEquals(expected.get(0).getSurname(), actual.get(0).getSurname());
        assertEquals(expected.get(0).getPhoneNumber(), actual.get(0).getPhoneNumber());
        assertEquals(expected.get(0).getEmailAdress(), actual.get(0).getEmailAdress());

        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(1).getFirstname(), actual.get(1).getFirstname());
        assertEquals(expected.get(1).getSurname(), actual.get(1).getSurname());
        assertEquals(expected.get(1).getPhoneNumber(), actual.get(1).getPhoneNumber());
        assertEquals(expected.get(1).getEmailAdress(), actual.get(1).getEmailAdress());
    }

    @Test
    void customerById(){
        CustomerDto expected = customerDto1;

        Mockito
                .when(customerRepository.findById(customer1.getId()))
                .thenReturn(Optional.of(customer1));

        CustomerDto actual = customerService.customerById(customer1.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstname(), actual.getFirstname());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getEmailAdress(), actual.getEmailAdress());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }

    @Test
    void newCustomer(){
        CustomerDto exptected = customerDto1;

        Mockito
                .when(customerRepository.save(Mockito.any()))
                .thenReturn(customer1);

        CustomerDto actual = customerService.newCustomer(customerDto1);

        assertEquals(exptected.getId(), actual.getId());
        assertEquals(exptected.getFirstname(), actual.getFirstname());
        assertEquals(exptected.getSurname(), actual.getSurname());
        assertEquals(exptected.getEmailAdress(), actual.getEmailAdress());
        assertEquals(exptected.getPhoneNumber(), actual.getPhoneNumber());
    }

    @Test
    void updateCustomer(){
        CustomerDto newCustomerDto = customerDto2;

        Mockito
                .when(customerRepository.findById(customer1.getId()))
                .thenReturn(Optional.of(customer1));

        Mockito
                .when(customerRepository.save(customer1))
                .thenReturn(customer1);

        customerService.updateCustomer(customer1.getId(), newCustomerDto);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(customer1.getId());
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer1);
    }

    @Test
    void deleteCustomer(){
        customerService.deleteCustomer(customer1.getId());

        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customer1.getId());
    }

}