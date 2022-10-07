package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.OnderdeelDto;
import nl.belastingdienst.autogarage.model.Onderdeel;
import nl.belastingdienst.autogarage.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> nieuwOnderdeel(@RequestBody Onderdeel onderdeel){
        onderdeelService.nieuwOnderdeel(onderdeel);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOnderdeel(@PathVariable Long id, @RequestBody Onderdeel nieuwOnderdeel){
        onderdeelService.updateOnderdeel(id, nieuwOnderdeel);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderOnderdeel(@PathVariable Long id){
        onderdeelService.verwijderOnderdeel(id);
        return ResponseEntity.ok().build();
    }
}
