package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.UploadResponse;
import nl.belastingdienst.autogarage.service.FileService;
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
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping
    public List<String> downloadAll(){
        return fileService.download();
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request){

        Resource resource = fileService.downloadFile(filename);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e){
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    @PostMapping
    public UploadResponse uploadFile(@RequestParam("file") MultipartFile file){

        String filename = fileService.uploadFile(file);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(filename).toUriString();

        String fileType =  file.getContentType();

        return new UploadResponse(filename, fileType, url);


    }

}
