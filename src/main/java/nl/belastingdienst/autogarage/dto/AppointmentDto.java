package nl.belastingdienst.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {

    @NotNull
    private Long id;

    private LocalDateTime startAppointment;

    private LocalDateTime endAppointment;

    public AppointmentDto(LocalDateTime startAppointment, LocalDateTime endAppointment) {
        this.startAppointment = startAppointment;
        this.endAppointment = endAppointment;
    }
}
