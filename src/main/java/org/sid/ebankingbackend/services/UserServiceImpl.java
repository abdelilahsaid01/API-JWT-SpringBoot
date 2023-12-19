package org.sid.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.Role;
import org.sid.ebankingbackend.entities.User;
//import org.sid.ebankingbackend.mappers.UserMapper;
//import org.sid.ebankingbackend.mappers.UserMapper;
import org.sid.ebankingbackend.mappers.UserMapperOld;
import org.sid.ebankingbackend.repositories.RoleRepository;
import org.sid.ebankingbackend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Getter
@Setter
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapperOld userMapperOld;
    private PasswordEncoder passwordEncoder;
    //private UserMapper userMapper;

    @Override
    public List<UserDTO> getUsers() {
        List<User>  users = userRepository.findAll();
    /*
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user-> {
            userDTOS.add(userMapper.fromUser(user));
        });
        return userDTOS;
 */
        return  users.stream().map(user -> userMapperOld.fromUser(user)).collect(Collectors.toList());

    }

    @Override
    public UserDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=  userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("Wrong username " + username + " in SecurityContexHolder"));
        return userMapperOld.fromUser(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        List<Role> roles =  roleRepository.findByNameIn(userDTO.getRoles());
        User user = new User();
        BeanUtils.copyProperties(userDTO ,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        return userMapperOld.fromUser(userRepository.save(user));
    }
}
