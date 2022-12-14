package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AppointmentDto;
import nl.belastingdienst.autogarage.exception.AppointmentNotFoundException;
import nl.belastingdienst.autogarage.model.Appointment;
import nl.belastingdienst.autogarage.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppointmentDto> allAppointments(){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for(Appointment appointment : appointmentList){
            appointmentDtoList.add(fromAppointmentToDto(appointment));
        }
        return appointmentDtoList;
    }

    public AppointmentDto appointmentById(Long id){
        return fromAppointmentToDto(appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id)));
    }

    public AppointmentDto newAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
        return fromAppointmentToDto(appointment);
    }

    public void updateAppointment(Long id, Appointment newAppointment){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
        newAppointment.setId(appointment.getId());
        appointmentRepository.save(newAppointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }

    private AppointmentDto fromAppointmentToDto(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto(appointment.getStartAppointment(), appointment.getEndAppointment());
        appointmentDto.setId(appointment.getId());
        return appointmentDto;
    }
}
