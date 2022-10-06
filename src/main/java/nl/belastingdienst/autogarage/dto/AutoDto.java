package nl.belastingdienst.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AutoDto {

    @NotNull
    private Long id;

    private String merk;
    private String model;
    private String bouwjaar;
    @Length(min = 6, max = 8)
    private String kenteken;

    public AutoDto(String merk, String model, String bouwjaar, String kenteken) {
        this.merk = merk;
        this.model = model;
        this.bouwjaar = bouwjaar;
        this.kenteken = kenteken;
    }
}
