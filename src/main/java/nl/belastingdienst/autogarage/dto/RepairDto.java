package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepairDto {

    @NotNull
    private Long id;
    private String name;
    private double price;

    public RepairDto(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
