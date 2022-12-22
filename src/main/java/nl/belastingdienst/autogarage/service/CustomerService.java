package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.CustomerDto;
import nl.belastingdienst.autogarage.exception.CarNotFoundException;
import nl.belastingdienst.autogarage.exception.CustomerNotFoundException;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.repository.CarRepository;
import nl.belastingdienst.autogarage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

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

    public CustomerDto newCustomer(CustomerDto customerInputDto){
        Customer customer = fromDtoToCustomer(customerInputDto);
        customerRepository.save(customer);
        return fromCustomerToDto(customer);
    }

    public void updateCustomer(Long id, CustomerDto customerInputDto){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setFirstname(customerInputDto.getFirstname());
        customer.setSurname(customerInputDto.getSurname());
        customer.setEmailAdress(customerInputDto.getEmailAdress());
        customer.setPhoneNumber(customerInputDto.getPhoneNumber());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    private CustomerDto fromCustomerToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto(customer.getFirstname(), customer.getSurname(), customer.getPhoneNumber(), customer.getEmailAdress());
        customerDto.setId(customer.getId());
        return customerDto;
    }

    private Customer fromDtoToCustomer(CustomerDto customerDto){
        Customer customer = new Customer(customerDto.getFirstname(), customerDto.getSurname(), customerDto.getPhoneNumber(), customerDto.getEmailAdress());
        customer.setId(customerDto.getId());
        return customer;
    }

    public void addCar(Long customerId, Long carId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        customer.addToCarlist(car);
        car.setCustomer(customer);
        customerRepository.save(customer);
        carRepository.save(car);
    }

    public void removeCar(Long customerId, Long carId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        if(customer.getCarList().contains(car)) {
            customer.removeFromCarList(car);
            car.setCustomer(null);
            customerRepository.save(customer);
            carRepository.save(car);
        }
        else{
            throw new CarNotFoundException("Car was not linked to this customer");
        }
    }


}
