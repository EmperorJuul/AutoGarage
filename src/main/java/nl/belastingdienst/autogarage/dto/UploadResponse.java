package nl.belastingdienst.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadResponse {

    private String filename;
    private String filetype;
    private String url;

    public UploadResponse(String filename, String filetype, String url) {
        this.filename = filename;
        this.filetype = filetype;
        this.url = url;
    }


}
