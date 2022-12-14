package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.OnderdeelDto;
import nl.belastingdienst.autogarage.model.Part;
import nl.belastingdienst.autogarage.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/onderdeel")
public class OnderdeelController {

    @Autowired
    private OnderdeelService onderdeelService;

    @GetMapping
    public ResponseEntity<List<OnderdeelDto>> alleOnderdelen(){
        return ResponseEntity.ok(onderdeelService.alleOnderdelen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OnderdeelDto> onderdeelOpId(@PathVariable Long id){
        return ResponseEntity.ok(onderdeelService.onderdeelOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuwOnderdeel(@RequestBody Part part){
        OnderdeelDto onderdeelDto = onderdeelService.nieuwOnderdeel(part);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(onderdeelDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOnderdeel(@PathVariable Long id, @RequestBody Part nieuwPart){
        onderdeelService.updateOnderdeel(id, nieuwPart);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderOnderdeel(@PathVariable Long id){
        onderdeelService.verwijderOnderdeel(id);
        return ResponseEntity.noContent().build();
    }
}
