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

    public AppointmentDto newAppointment(AppointmentDto appointmentInputDto){
        Appointment appointment = fromDtoToAppointment(appointmentInputDto);
        appointmentRepository.save(appointment);
        return fromAppointmentToDto(appointment);
    }

    public void updateAppointment(Long id, AppointmentDto appointmentInputDto){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
        appointment.setStartAppointment(appointmentInputDto.getStartAppointment());
        appointment.setEndAppointment(appointmentInputDto.getEndAppointment());
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }

    private AppointmentDto fromAppointmentToDto(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto(appointment.getStartAppointment(), appointment.getEndAppointment());
        appointmentDto.setId(appointment.getId());
        return appointmentDto;
    }

    private Appointment fromDtoToAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment(appointmentDto.getStartAppointment(), appointmentDto.getEndAppointment());
        appointment.setId(appointmentDto.getId());
        return appointment;
    }
}
