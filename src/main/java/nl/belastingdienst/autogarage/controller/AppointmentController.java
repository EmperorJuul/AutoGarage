package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AppointmentDto;
import nl.belastingdienst.autogarage.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> allAppointments(){
        return ResponseEntity.ok(appointmentService.allAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> appointmentById(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.appointmentById(id));
    }

    @PostMapping
    public ResponseEntity<Object> newAppointment(@RequestBody AppointmentDto appointmentInputDto){
        AppointmentDto appointmentOutputDto =  appointmentService.newAppointment(appointmentInputDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(appointmentOutputDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto appointmentInputDto){
        appointmentService.updateAppointment(id, appointmentInputDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/repair/{appointmentId}/{repairId}")
    public ResponseEntity<Object> addRepair(@PathVariable Long appointmentId, @PathVariable Long repairId){
        appointmentService.addRepair(appointmentId, repairId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/repair/{appointmentId}/{repairId}")
    public ResponseEntity<Object> removeRepair(@PathVariable Long appointmentId, @PathVariable Long repairId){
        appointmentService.removeRepair(appointmentId, repairId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/car/{appointmentId}/{carId}")
    public ResponseEntity<Object> addCar(@PathVariable Long appointmentId, @PathVariable Long carId){
        appointmentService.addCar(appointmentId, carId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/car/{appointmentId}/{carId}")
    public ResponseEntity<Object> removeCar(@PathVariable Long appointmentId, @PathVariable Long carId) {
        appointmentService.removeCar(appointmentId, carId);
        return ResponseEntity.noContent().build();
    }
}
