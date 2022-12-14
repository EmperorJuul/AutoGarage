package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.CustomerDto;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.service.KlantService;
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
    private KlantService klantService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> alleKlanten(){
        return ResponseEntity.ok(klantService.alleKlanten());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> KlantOpId(@PathVariable Long id){
        return ResponseEntity.ok(klantService.klantOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweKlant(@RequestBody Customer customer){
        CustomerDto klantDto = klantService.nieuweKlant(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(klantDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKlant(@PathVariable Long id, @RequestBody Customer nieuweCustomer){
        klantService.updateKlant(id, nieuweCustomer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderKlant(@PathVariable Long id){
        klantService.verwijderKlant(id);
        return ResponseEntity.noContent().build();
    }
}
