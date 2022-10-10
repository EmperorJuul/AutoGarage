package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.GebruikerDto;
import nl.belastingdienst.autogarage.model.Gebruiker;
import nl.belastingdienst.autogarage.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/gebruiker")
public class GebruikerController {

    @Autowired
    private GebruikerService gebruikerService;

    @GetMapping
    public ResponseEntity<List<GebruikerDto>> alleGebruikers(){
        return ResponseEntity.ok(gebruikerService.alleGebruikers());
    }

    @GetMapping("/{gebruikersnaam}")
    public ResponseEntity<GebruikerDto> gebruikerOpGebruikersnaam(@PathVariable String gebruikersnaam){
        return ResponseEntity.ok(gebruikerService.gebruikerOpGebruikersnaam(gebruikersnaam));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweGebruiker(@RequestBody Gebruiker gebruiker){
        GebruikerDto gebruikerDto = gebruikerService.nieuweGebruiker(gebruiker);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{gebruikersnaam}")
                .buildAndExpand(gebruikerDto.getGebruikersnaam()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{gebruikersnaam}")
    public ResponseEntity<Object> updateGebruiker(@RequestBody Gebruiker nieuweGebruiker){
        gebruikerService.updateGebruiker(nieuweGebruiker);
        return ResponseEntity.noContent().build();
    }
}
