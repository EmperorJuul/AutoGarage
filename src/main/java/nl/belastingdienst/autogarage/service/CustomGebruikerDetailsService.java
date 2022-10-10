package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.exception.GebruikerNotFoundException;
import nl.belastingdienst.autogarage.model.Authoriteit;
import nl.belastingdienst.autogarage.model.Gebruiker;
import nl.belastingdienst.autogarage.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomGebruikerDetailsService implements UserDetailsService {

    @Autowired
    private GebruikerService gebruikerService;
    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) {
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam));


        String password = gebruiker.getWachtwoord();

        Set<Authoriteit> authorities = gebruiker.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authoriteit authoriteit : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authoriteit.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(gebruikersnaam, password, grantedAuthorities);
    }
}
