package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.UploadAntwoord;
import nl.belastingdienst.autogarage.service.BestandenService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bestand")
public class BestandenController {

    private final BestandenService bestandenService;

    public BestandenController(BestandenService bestandenService){
        this.bestandenService = bestandenService;
    }

    @GetMapping
    public List<String> downloadAll(){
        return bestandenService.downLoad();
    }

    @GetMapping("/{bestandsnaam}")
    public ResponseEntity<Resource> downloadBestand(@PathVariable String bestandsnaam, HttpServletRequest verzoek){

        Resource resource = bestandenService.downloadBestand(bestandsnaam);

        String mimeType;

        try {
            mimeType = verzoek.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e){
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    @PostMapping
    public UploadAntwoord uploadBestand(@RequestParam("bestand") MultipartFile bestand){

        String bestandsnaam = bestandenService.uploadBestand(bestand);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/bestand/").path(bestandsnaam).toUriString();

        String bestandsType =  bestand.getContentType();

        return new UploadAntwoord(bestandsnaam, bestandsType, url);


    }

}
