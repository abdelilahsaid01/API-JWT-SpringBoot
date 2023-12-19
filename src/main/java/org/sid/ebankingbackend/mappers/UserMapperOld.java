package org.sid.ebankingbackend.mappers;

import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.Role;
import org.sid.ebankingbackend.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapperOld {

    public UserDTO fromUser(User user) {
        UserDTO userDTO= new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        userDTO.setRoles(roles);
        return userDTO;
    }

    public User fromUserDTO(UserDTO userDTO) {
        User user= new User();
        BeanUtils.copyProperties(userDTO,user);
        //modelMapper.map(userDTO, User.class); ModelMapper
        return user;
    }
}
