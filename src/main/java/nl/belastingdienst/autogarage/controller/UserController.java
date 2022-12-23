package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.UserInputDto;
import nl.belastingdienst.autogarage.dto.UserOutputDto;
import nl.belastingdienst.autogarage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> allUsers(){
        return ResponseEntity.ok(userService.allUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserOutputDto> userByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.userByUsername(username));
    }

    @PostMapping
    public ResponseEntity<Object> newUser(@RequestBody UserInputDto userInputDto){
        UserOutputDto userOutputDto = userService.newUser(userInputDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(userInputDto.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable String username, @RequestBody UserInputDto newUser){
        userService.updateUser(username, newUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}/authority")
    public ResponseEntity<Object> getAuthority(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getAuthorities(username));
    }

    @PostMapping("/{username}/authority")
    public ResponseEntity<Object> addAuthority(@PathVariable("username") String username, String authority){
        userService.addAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}/authority")
    public ResponseEntity<Object> removeAuthority(@PathVariable("username") String username, String authority){
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

}
