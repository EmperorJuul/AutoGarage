package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReparatieDto {

    @NotNull
    private Long id;
    private String naam;
    private double prijs;

    public ReparatieDto(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }
}
