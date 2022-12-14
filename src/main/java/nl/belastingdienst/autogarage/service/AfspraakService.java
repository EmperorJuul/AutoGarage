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
public class AfspraakService {

    @Autowired
    private AppointmentRepository afspraakRepository;

    public List<AppointmentDto> alleAfspraken(){
        List<Appointment> appointmentList = afspraakRepository.findAll();
        List<AppointmentDto> afspraakDtoList = new ArrayList<>();
        for(Appointment appointment : appointmentList){
            afspraakDtoList.add(vanAfspraakNaarAfspraakDto(appointment));
        }
        return afspraakDtoList;
    }

    public AppointmentDto afspraakOpId(Long id){
        return vanAfspraakNaarAfspraakDto(afspraakRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id)));
    }

    public AppointmentDto nieuweAfspraak(Appointment appointment){
        afspraakRepository.save(appointment);
        return vanAfspraakNaarAfspraakDto(appointment);
    }

    public void updateAfspraak(Long id, Appointment nieuweAppointment){
        Appointment appointment = afspraakRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
        nieuweAppointment.setId(appointment.getId());
        afspraakRepository.save(nieuweAppointment);
    }

    public void verwijderAfspraak(Long id){
        afspraakRepository.deleteById(id);
    }

    private AppointmentDto vanAfspraakNaarAfspraakDto(Appointment appointment){
        AppointmentDto afspraakDto = new AppointmentDto(appointment.getStartAppointment(), appointment.getEndAppointment());
        afspraakDto.setId(appointment.getId());
        return afspraakDto;
    }
}
