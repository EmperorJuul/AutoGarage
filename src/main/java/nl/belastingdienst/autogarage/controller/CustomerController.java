package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.CustomerDto;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> allCustomers(){
        return ResponseEntity.ok(customerService.allCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> customerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.customerById(id));
    }

    @PostMapping
    public ResponseEntity<Object> newCustomer(@RequestBody Customer customer){
        CustomerDto customerDto = customerService.newCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customerDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomer){
        customerService.updateCustomer(id, newCustomer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}