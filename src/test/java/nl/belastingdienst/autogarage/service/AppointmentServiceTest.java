package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AppointmentDto;
import nl.belastingdienst.autogarage.model.Appointment;
import nl.belastingdienst.autogarage.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository afspraakRepository;

    @InjectMocks
    AfspraakService afspraakService;

    @Mock
    Appointment appointment1;
    @Mock
    Appointment appointment2;

    @BeforeEach
    public void setup(){
        appointment1 = new Appointment(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        appointment1.setId(1L);
        appointment2 = new Appointment(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        appointment2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }


    @Test
    void alleAfspraken(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);

        AppointmentDto afspraakDto1 = new AppointmentDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        afspraakDto1.setId(1L);
        AppointmentDto afspraakDto2 = new AppointmentDto(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        afspraakDto2.setId(2L);
        List<AppointmentDto> verwacht = new ArrayList<>();
        verwacht.add(afspraakDto1);
        verwacht.add(afspraakDto2);

        Mockito
                .when(afspraakRepository.findAll())
                .thenReturn(appointmentList);

        List<AppointmentDto> uitkomst =  afspraakService.alleAfspraken();

        assertEquals(verwacht.get(0).getId(), uitkomst.get(0).getId());
        assertEquals(verwacht.get(0).getStartAppointment(), uitkomst.get(0).getStartAppointment());
        assertEquals(verwacht.get(0).getEndAppointment(), uitkomst.get(0).getEndAppointment());

        assertEquals(verwacht.get(1).getId(), uitkomst.get(1).getId());
        assertEquals(verwacht.get(1).getStartAppointment(), uitkomst.get(1).getStartAppointment());
        assertEquals(verwacht.get(1).getEndAppointment(), uitkomst.get(1).getEndAppointment());
    }

    @Test
    void afspraakOpId(){
        AppointmentDto verwacht = new AppointmentDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        verwacht.setId(1L);

        Mockito
                .when(afspraakRepository.findById(appointment1.getId()))
                .thenReturn(Optional.of(appointment1));

        AppointmentDto uitkomst = afspraakService.afspraakOpId(appointment1.getId());

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getStartAppointment(), uitkomst.getStartAppointment());
        assertEquals(verwacht.getEndAppointment(), uitkomst.getEndAppointment());

    }


    @Test
    void nieuweAfspraak(){
        AppointmentDto verwacht = new AppointmentDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        verwacht.setId(1L);

        Mockito
                .when(afspraakRepository.save(appointment1))
                .thenReturn(appointment1);

        AppointmentDto uitkomst = afspraakService.nieuweAfspraak(appointment1);

        assertEquals(verwacht.getId(), uitkomst.getId());
        assertEquals(verwacht.getStartAppointment(), uitkomst.getStartAppointment());
        assertEquals(verwacht.getEndAppointment(), uitkomst.getEndAppointment());
    }


    @Test
    void updateAfspraak(){
        Appointment nieuweAppointment = new Appointment(LocalDateTime.of(2020,10,6,12,30), LocalDateTime.of(2020,10,6,13,30));
        nieuweAppointment.setId(1L);

        Mockito
                .when(afspraakRepository.findById(appointment1.getId()))
                .thenReturn(Optional.of(appointment1));

        Mockito
                .when(afspraakRepository.save(nieuweAppointment))
                .thenReturn(nieuweAppointment);

        afspraakService.updateAfspraak(nieuweAppointment.getId(), nieuweAppointment);

        Mockito.verify(afspraakRepository, Mockito.times(1)).findById(appointment1.getId());
        Mockito.verify(afspraakRepository, Mockito.times(1)).save(nieuweAppointment);
    }


    @Test
    void verwijderAfspraak(){
        afspraakService.verwijderAfspraak(appointment1.getId());

        Mockito.verify(afspraakRepository, Mockito.times(1)).deleteById(appointment1.getId());
    }



}