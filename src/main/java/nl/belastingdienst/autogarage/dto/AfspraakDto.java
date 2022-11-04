package nl.belastingdienst.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AfspraakDto {

    @NotNull
    private Long id;

    private LocalDateTime beginAfsrpaak;

    private LocalDateTime eindeAfspraak;

    public AfspraakDto(LocalDateTime beginAfsrpaak, LocalDateTime eindeAfspraak) {
        this.beginAfsrpaak = beginAfsrpaak;
        this.eindeAfspraak = eindeAfspraak;
    }
}
