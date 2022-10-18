package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.exception.GebruikerNotFoundException;
import nl.belastingdienst.autogarage.model.Authority;
import nl.belastingdienst.autogarage.model.Gebruiker;
import nl.belastingdienst.autogarage.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException {
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new GebruikerNotFoundException(gebruikersnaam));

        String wachtwoord = gebruiker.getWachtwoord();

        Set<Authority> authorities = gebruiker.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(gebruikersnaam, wachtwoord, grantedAuthorities);
    }
}