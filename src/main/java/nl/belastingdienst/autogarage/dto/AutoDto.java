package nl.belastingdienst.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoDto {

    @NotNull
    private Long id;

    private String merk;
    private String model;
    private String bouwjaar;
    private String kenteken;

    public AutoDto(String merk, String model, String bouwjaar, String kenteken) {
        this.merk = merk;
        this.model = model;
        this.bouwjaar = bouwjaar;
        this.kenteken = kenteken;
    }
}
