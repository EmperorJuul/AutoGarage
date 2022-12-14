package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.UserDto;
import nl.belastingdienst.autogarage.exception.UserNotFoundException;
import nl.belastingdienst.autogarage.model.Authority;
import nl.belastingdienst.autogarage.model.User;
import nl.belastingdienst.autogarage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> allUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            userDtoList.add(fromUserToDto(user));
        }
        return userDtoList;
    }

    public UserDto userByUsername(String username){
        return fromUserToDto(userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    public UserDto newUser(User user){
        userRepository.save(user);
        return fromUserToDto(user);
    }

    public void updateUser(String username, User newUser){
        userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        userRepository.deleteById(username);
        userRepository.save(newUser);
    }

    public void deleteUsername(String username){
        userRepository.deleteById(username);
    }

    public Set<Authority> getAuthorities(String username){
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        UserDto userDto = fromUserToDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority){
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority){
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
    }

    private UserDto fromUserToDto(User user){
        UserDto userDto = new UserDto(user.getUsername(), user.getAuthorities());
        return userDto;
    }


}
