package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AppointmentDto;
import nl.belastingdienst.autogarage.model.Appointment;
import nl.belastingdienst.autogarage.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/afspraak")
public class AfspraakController {

    @Autowired
    private AfspraakService afspraakService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> alleAfspraken(){
        return ResponseEntity.ok(afspraakService.alleAfspraken());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> afspraakOpId(@PathVariable Long id){
        return ResponseEntity.ok(afspraakService.afspraakOpId(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAfspraak(@RequestBody Appointment appointment){
        AppointmentDto afspraakDto =  afspraakService.nieuweAfspraak(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(afspraakDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAfspraak(@PathVariable Long id, @RequestBody Appointment nieuweAppointment){
        afspraakService.updateAfspraak(id, nieuweAppointment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAfspraak(@PathVariable Long id){
        afspraakService.verwijderAfspraak(id);
        return ResponseEntity.noContent().build();
    }
}
