package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OnderdeelDto {

    @NotNull
    private Long id;
    private String naam;
    private String merk;

    public OnderdeelDto(String naam, String merk) {
        this.naam = naam;
        this.merk = merk;
    }
}
