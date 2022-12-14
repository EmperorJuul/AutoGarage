package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PartDto {

    @NotNull
    private Long id;
    private String name;
    private String brand;

    public PartDto(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }
}
