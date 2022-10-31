package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadAntwoord {

    private String bestandsnaam;
    private String bestandsType;
    private String url;

    public UploadAntwoord(String bestandsnaam, String bestandsType, String url) {
        this.bestandsnaam = bestandsnaam;
        this.bestandsType = bestandsType;
        this.url = url;
    }


}
