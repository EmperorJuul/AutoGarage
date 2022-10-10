package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.security.JwtUtil;
import nl.belastingdienst.autogarage.service.CustomGebruikerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthenticatieController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private CustomGebruikerDetailsService gebruikerDetailsService;
    private JwtUtil jwtUtil;

    @GetMapping("/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal){
        return ResponseEntity.ok().body(principal);
    }
}
