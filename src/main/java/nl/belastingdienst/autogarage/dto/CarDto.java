package nl.belastingdienst.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CarDto {

    @NotNull
    private Long id;

    private String brand;
    private String model;
    private int year;
    @Length(min = 6, max = 8)
    private String licenseplate;

    public CarDto(String brand, String model, int year, String licenseplate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licenseplate = licenseplate;
    }
}
