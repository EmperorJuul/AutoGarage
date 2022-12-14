package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AppointmentDto;
import nl.belastingdienst.autogarage.model.Appointment;
import nl.belastingdienst.autogarage.service.AppointmentService;
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
    private AppointmentService afspraakService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> alleAfspraken(){
        return ResponseEntity.ok(afspraakService.allAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> afspraakOpId(@PathVariable Long id){
        return ResponseEntity.ok(afspraakService.appointmentById(id));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweAfspraak(@RequestBody Appointment appointment){
        AppointmentDto afspraakDto =  afspraakService.newAppointment(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(afspraakDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAfspraak(@PathVariable Long id, @RequestBody Appointment nieuweAppointment){
        afspraakService.updateAppointment(id, nieuweAppointment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> verwijderAfspraak(@PathVariable Long id){
        afspraakService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
