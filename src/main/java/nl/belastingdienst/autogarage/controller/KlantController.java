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
@RequestMapping("/klant")
public class KlantController {

    @Autowired
    private CustomerService klantService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> alleKlanten(){
        return ResponseEntity.ok(klantService.allCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> KlantOpId(@PathVariable Long id){
        return ResponseEntity.ok(klantService.customerById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweKlant(@RequestBody Customer customer){
        CustomerDto klantDto = klantService.newCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(klantDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKlant(@PathVariable Long id, @RequestBody Customer nieuweCustomer){
        klantService.updateCustomer(id, nieuweCustomer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderKlant(@PathVariable Long id){
        klantService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
