package nl.belastingdienst.autogarage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BestandenService {

    @Value("bestanden")
    private Path mapPad;

    private final String lokaalPad;

    public BestandenService(@Value("bestanden") String lokaalPad){
        mapPad = Paths.get(lokaalPad).toAbsolutePath().normalize();

        this.lokaalPad = lokaalPad;

        try {
            Files.createDirectories(mapPad);
        } catch (IOException e){
            throw new RuntimeException("Probleem met aanmaken map", e);
        }
    }

    public String uploadBestand(MultipartFile bestand){

        String bestandsnaam = StringUtils.cleanPath(Objects.requireNonNull(bestand.getOriginalFilename()));

        Path volledigPad = Paths.get(mapPad + File.separator + bestandsnaam);

        try {
            Files.copy(bestand.getInputStream(), volledigPad, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Probleem bij het opslaan van het bestand", e);
        }
        return bestandsnaam;
    }

    public Resource downloadBestand(String bestandsnaam){

        Path pad = Paths.get(lokaalPad).toAbsolutePath().resolve(bestandsnaam);

        Resource resource;

        try {
            resource = new UrlResource(pad.toUri());
        } catch (MalformedURLException e){
            throw new RuntimeException("Probleem bij het lezen van het bestand", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Bestand bestaat niet of is niet leesbaar");
        }
    }

    public List<String> downLoad() {
        // Directory path here
        var list = new ArrayList<String>();
        File folder = new File(lokaalPad);
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++){
            if(listOfFiles[i].isFile()){
                String name = listOfFiles[i].getName();
                list.add(name);
            }
        }
        return list;
    }
}
