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
public class FileService {

    @Value("files")
    private Path folderPath;

    private final String localPath;

    public FileService(@Value("files") String localPath){
        folderPath = Paths.get(localPath).toAbsolutePath().normalize();

        this.localPath = localPath;

        try {
            Files.createDirectories(folderPath);
        } catch (IOException e){
            throw new RuntimeException("Problem creating folder ", e);
        }
    }

    public String uploadFile(MultipartFile file){

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path fullPath = Paths.get(folderPath + File.separator + filename);

        try {
            Files.copy(file.getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Error saving file ", e);
        }
        return filename;
    }

    public Resource downloadFile(String filename){

        Path pad = Paths.get(localPath).toAbsolutePath().resolve(filename);

        Resource resource;

        try {
            resource = new UrlResource(pad.toUri());
        } catch (MalformedURLException e){
            throw new RuntimeException("Error trying to read file ", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("File does not exist or is corrupted");
        }
    }

    public List<String> downLoad() {
        // Directory path here
        var list = new ArrayList<String>();
        File folder = new File(localPath);
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
