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
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Mock
    Appointment appointment1;
    @Mock
    Appointment appointment2;
    @Mock
    AppointmentDto appointmentDto1;
    @Mock
    AppointmentDto appointmentDto2;

    @BeforeEach
    public void setup(){
        appointment1 = new Appointment(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        appointment1.setId(1L);
        appointment2 = new Appointment(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        appointment2.setId(2L);
        appointmentDto1 = new AppointmentDto(LocalDateTime.of(2020,10,6,10,00), LocalDateTime.of(2020, 10,6,11,00));
        appointmentDto1.setId(1L);
        appointmentDto2 = new AppointmentDto(LocalDateTime.of(2020,10,6,11,30), LocalDateTime.of(2020,10,6,12,00));
        appointmentDto2.setId(2L);
        //Id wordt in het programma geregeld door springboot
        //om de test te laten slagen worden ze hier handmatig overschreven
    }


    @Test
    void allAppointments(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        List<AppointmentDto> expected = new ArrayList<>();
        expected.add(appointmentDto1);
        expected.add(appointmentDto2);

        Mockito
                .when(appointmentRepository.findAll())
                .thenReturn(appointmentList);

        List<AppointmentDto> actual =  appointmentService.allAppointments();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getStartAppointment(), actual.get(0).getStartAppointment());
        assertEquals(expected.get(0).getEndAppointment(), actual.get(0).getEndAppointment());

        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(1).getStartAppointment(), actual.get(1).getStartAppointment());
        assertEquals(expected.get(1).getEndAppointment(), actual.get(1).getEndAppointment());
    }

    @Test
    void appointmentById(){
        AppointmentDto expected = appointmentDto1;

        Mockito
                .when(appointmentRepository.findById(appointment1.getId()))
                .thenReturn(Optional.of(appointment1));

        AppointmentDto actual = appointmentService.appointmentById(appointment1.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getStartAppointment(), actual.getStartAppointment());
        assertEquals(expected.getEndAppointment(), actual.getEndAppointment());

    }


    @Test
    void newAppointment(){
        AppointmentDto expected = appointmentDto1;

        Mockito
                .when(appointmentRepository.save(Mockito.any()))
                .thenReturn(appointment1);

        AppointmentDto actual = appointmentService.newAppointment(appointmentDto1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getStartAppointment(), actual.getStartAppointment());
        assertEquals(expected.getEndAppointment(), actual.getEndAppointment());
    }


    @Test
    void updateAppointment(){
        AppointmentDto newAppointmentDto = appointmentDto2;

        Mockito
                .when(appointmentRepository.findById(appointment1.getId()))
                .thenReturn(Optional.of(appointment1));

        Mockito
                .when(appointmentRepository.save(appointment1))
                .thenReturn(appointment1);

        appointmentService.updateAppointment(appointment1.getId(), newAppointmentDto);

        Mockito.verify(appointmentRepository, Mockito.times(1)).findById(appointment1.getId());
        Mockito.verify(appointmentRepository, Mockito.times(1)).save(appointment1);
    }


    @Test
    void deleteAppointment(){
        appointmentService.deleteAppointment(appointment1.getId());

        Mockito.verify(appointmentRepository, Mockito.times(1)).deleteById(appointment1.getId());
    }



}