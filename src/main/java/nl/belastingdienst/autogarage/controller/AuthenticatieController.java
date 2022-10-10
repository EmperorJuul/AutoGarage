package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.dto.AuthenticatieAntwoord;
import nl.belastingdienst.autogarage.dto.AuthenticatieVerzoek;
import nl.belastingdienst.autogarage.security.JwtUtil;
import nl.belastingdienst.autogarage.service.CustomGebruikerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthenticatieController {

    @Autowired
    private AuthenticationManager authenticatieManager;
    @Autowired
    private CustomGebruikerDetailsService gebruikerDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal){
        return ResponseEntity.ok().body(principal);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> maakToken(@RequestBody AuthenticatieVerzoek authenticatieVerzoek) throws Exception{

        String gebruikersnaam = authenticatieVerzoek.getGebruikersnaam();
        String wachtwoord = authenticatieVerzoek.getWachtwoord();

        try {
            authenticatieManager.authenticate(
                    new UsernamePasswordAuthenticationToken(gebruikersnaam, wachtwoord)
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Verkeerde gebruikersnaam of wachtwoord", e);
        }

        final UserDetails userDetails = gebruikerDetailsService.loadUserByUsername(gebruikersnaam);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticatieAntwoord(jwt));
    }
}
