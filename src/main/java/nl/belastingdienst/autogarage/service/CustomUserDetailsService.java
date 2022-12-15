package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.exception.UserNotFoundException;
import nl.belastingdienst.autogarage.model.Authority;
import nl.belastingdienst.autogarage.model.User;
import nl.belastingdienst.autogarage.repository.UserRepository;
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
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));

        String password = user.getPassword();

        Set<Authority> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }
}
