package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.UserDto;
import nl.belastingdienst.autogarage.exception.UserNotFoundException;
import nl.belastingdienst.autogarage.model.Authority;
import nl.belastingdienst.autogarage.model.User;
import nl.belastingdienst.autogarage.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GebruikerService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    public List<UserDto> alleGebruikers(){
        List<User> userList = gebruikerRepository.findAll();
        List<UserDto> gebruikerDtoList = new ArrayList<>();
        for(User user : userList){
            gebruikerDtoList.add(vanGebruikerNaarGebruikerDto(user));
        }
        return gebruikerDtoList;
    }

    public UserDto gebruikerOpGebruikersnaam(String gebruikersnaam){
        return vanGebruikerNaarGebruikerDto(gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new UserNotFoundException(gebruikersnaam)));
    }

    public UserDto nieuweGebruiker(User user){
        gebruikerRepository.save(user);
        return vanGebruikerNaarGebruikerDto(user);
    }

    public void updateGebruiker(String gebruikersnaam, User nieuweUser){
        User user = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new UserNotFoundException(gebruikersnaam));
        nieuweUser.setUsername(user.getUsername());
        gebruikerRepository.save(nieuweUser);
    }

    public void verwijderGebruiker(String gebruikersnaam){
        gebruikerRepository.deleteById(gebruikersnaam);
    }

    public Set<Authority> getAuthorities(String gebruikersnaam){
        User user = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new UserNotFoundException(gebruikersnaam));
        UserDto gebruikerDto = vanGebruikerNaarGebruikerDto(user);
        return gebruikerDto.getAuthorities();
    }

    public void addAuthority(String gebruikersnaam, String authority){
        User user = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new UserNotFoundException(gebruikersnaam));
        user.addAuthority(new Authority(gebruikersnaam, authority));
        gebruikerRepository.save(user);
    }

    public void removeAuthority(String gebruikersnaam, String authority){
        User user = gebruikerRepository.findById(gebruikersnaam).orElseThrow(() -> new UserNotFoundException(gebruikersnaam));
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
    }

    private UserDto vanGebruikerNaarGebruikerDto(User user){
        UserDto gebruikerDto = new UserDto(user.getUsername(), user.getAuthorities());
        return gebruikerDto;
    }


}
