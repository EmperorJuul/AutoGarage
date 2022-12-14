package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AfspraakDto;
import nl.belastingdienst.autogarage.exception.AfspraakNotFoundException;
import nl.belastingdienst.autogarage.model.Appointment;
import nl.belastingdienst.autogarage.repository.AfspraakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AfspraakService {

    @Autowired
    private AfspraakRepository afspraakRepository;

    public List<AfspraakDto> alleAfspraken(){
        List<Appointment> appointmentList = afspraakRepository.findAll();
        List<AfspraakDto> afspraakDtoList = new ArrayList<>();
        for(Appointment appointment : appointmentList){
            afspraakDtoList.add(vanAfspraakNaarAfspraakDto(appointment));
        }
        return afspraakDtoList;
    }

    public AfspraakDto afspraakOpId(Long id){
        return vanAfspraakNaarAfspraakDto(afspraakRepository.findById(id).orElseThrow(() -> new AfspraakNotFoundException(id)));
    }

    public AfspraakDto nieuweAfspraak(Appointment appointment){
        afspraakRepository.save(appointment);
        return vanAfspraakNaarAfspraakDto(appointment);
    }

    public void updateAfspraak(Long id, Appointment nieuweAppointment){
        Appointment appointment = afspraakRepository.findById(id).orElseThrow(() -> new AfspraakNotFoundException(id));
        nieuweAppointment.setId(appointment.getId());
        afspraakRepository.save(nieuweAppointment);
    }

    public void verwijderAfspraak(Long id){
        afspraakRepository.deleteById(id);
    }

    private AfspraakDto vanAfspraakNaarAfspraakDto(Appointment appointment){
        AfspraakDto afspraakDto = new AfspraakDto(appointment.getStartAppointment(), appointment.getEndAppointment());
        afspraakDto.setId(appointment.getId());
        return afspraakDto;
    }
}
