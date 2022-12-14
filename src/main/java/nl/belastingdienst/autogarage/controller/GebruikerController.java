package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.UserDto;
import nl.belastingdienst.autogarage.model.User;
import nl.belastingdienst.autogarage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/gebruiker")
public class GebruikerController {

    @Autowired
    private UserService gebruikerService;

    @GetMapping
    public ResponseEntity<List<UserDto>> alleGebruikers(){
        return ResponseEntity.ok(gebruikerService.allUsers());
    }

    @GetMapping("/{gebruikersnaam}")
    public ResponseEntity<UserDto> gebruikerOpGebruikersnaam(@PathVariable String gebruikersnaam){
        return ResponseEntity.ok(gebruikerService.userByUsername(gebruikersnaam));
    }

    @PostMapping
    public ResponseEntity<Object> nieuweGebruiker(@RequestBody User user){
        UserDto gebruikerDto = gebruikerService.newUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{gebruikersnaam}")
                .buildAndExpand(gebruikerDto.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{gebruikersnaam}")
    public ResponseEntity<Object> updateGebruiker(@PathVariable String gebruikersnaam, @RequestBody User nieuweUser){
        gebruikerService.updateUser(gebruikersnaam, nieuweUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{gebruikersnaam}")
    public ResponseEntity<Object> verwijderGebruiker(@PathVariable String gebruikersnaam){
        gebruikerService.deleteUsername(gebruikersnaam);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{gebruikersnaam}/authority")
    public ResponseEntity<Object> getAuthority(@PathVariable("gebruikersnaam") String gebruikersnaam){
        return ResponseEntity.ok(gebruikerService.getAuthorities(gebruikersnaam));
    }

    @PostMapping("/{gebruikersnaam}/authority")
    public ResponseEntity<Object> addAuthority(@PathVariable("gebruikersnaam") String gebruikersnaam, String authority){
        gebruikerService.addAuthority(gebruikersnaam, authority);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{gebruikersnaam}/authority")
    public ResponseEntity<Object> removeAuthority(@PathVariable("gebruikersnaam") String gebruikersnaam, String authority){
        gebruikerService.removeAuthority(gebruikersnaam, authority);
        return ResponseEntity.noContent().build();
    }

}
