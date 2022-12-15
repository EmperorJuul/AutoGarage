package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.CustomerDto;
import nl.belastingdienst.autogarage.exception.CustomerNotFoundException;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> allCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for(Customer customer : customerList){
            customerDtoList.add(fromCustomerToDto(customer));
        }
        return customerDtoList;
    }

    public CustomerDto customerById(Long id){
        return fromCustomerToDto(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public CustomerDto newCustomer(Customer customer){
        customerRepository.save(customer);
        return fromCustomerToDto(customer);
    }

    public void updateCustomer(Long id, Customer newCustomer){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        newCustomer.setId(customer.getId());
        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    private CustomerDto fromCustomerToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto(customer.getFirstname(), customer.getSurname(), customer.getPhoneNumber(), customer.getEmailAdress());
        customerDto.setId(customer.getId());
        return customerDto;
    }
}
