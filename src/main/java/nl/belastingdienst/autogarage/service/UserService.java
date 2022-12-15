package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.UserInputDto;
import nl.belastingdienst.autogarage.dto.UserOutputDto;
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

    public List<UserOutputDto> allUsers(){
        List<User> userList = userRepository.findAll();
        List<UserOutputDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            userDtoList.add(fromUserToDto(user));
        }
        return userDtoList;
    }

    public UserOutputDto userByUsername(String username){
        return fromUserToDto(userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    public UserOutputDto newUser(UserInputDto userInputDto){
        User user = fromDtoToUser(userInputDto);
        userRepository.save(user);
        return fromUserToDto(user);
    }

    public void updateUser(String username, UserInputDto newUser){
        userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        userRepository.deleteById(username);
        User user = fromDtoToUser(newUser);
        userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

    public Set<Authority> getAuthorities(String username){
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        UserOutputDto userDto = fromUserToDto(user);
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

    private UserOutputDto fromUserToDto(User user){
        UserOutputDto userDto = new UserOutputDto(user.getUsername(), user.getAuthorities());
        return userDto;
    }

    private User fromDtoToUser(UserInputDto userDto){
        User user = new User(userDto.getUsername(), userDto.getPassword());
        return user;
    }


}
