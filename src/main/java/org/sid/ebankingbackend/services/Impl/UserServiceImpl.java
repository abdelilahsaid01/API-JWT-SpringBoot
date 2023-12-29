package org.sid.ebankingbackend.services.Impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.Role;
import org.sid.ebankingbackend.entities.User;
//import org.sid.ebankingbackend.mappers.UserMapper;
//import org.sid.ebankingbackend.mappers.UserMapper;
import org.sid.ebankingbackend.entities.UserElastic;
import org.sid.ebankingbackend.mappers.UserMapperOld;
import org.sid.ebankingbackend.repositories.RoleRepository;
import org.sid.ebankingbackend.repositories.UserElasticRepository;
import org.sid.ebankingbackend.repositories.UserRepository;
import org.sid.ebankingbackend.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserElasticRepository userElasticRepository;
    private RoleRepository roleRepository;
    private UserMapperOld userMapperOld;
    private PasswordEncoder passwordEncoder;
    //private UserMapper userMapper;

    @Cacheable(value = "product", key = "1")
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

    @CacheEvict(cacheNames = "product", beforeInvocation = true)
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @CachePut(cacheNames = "product")

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userMapperOld.fromUserDTO(userDTO);
        List<Role> roles =  roleRepository.findByNameIn(userDTO.getRoles());
        user.setRoles(roles);
        User userUpdated =  userRepository.save(user);
        return userMapperOld.fromUser(userUpdated);
    }

    @Override
    public UserDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=  userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("Wrong username " + username + " in SecurityContexHolder"));
        return userMapperOld.fromUser(user);
        //return userMapper.userToUserDTO(user);
    }

    @CacheEvict(cacheNames = "product", beforeInvocation = true)

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        List<Role> roles =  roleRepository.findByNameIn(userDTO.getRoles());
        User user = new User();
        BeanUtils.copyProperties(userDTO ,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        return userMapperOld.fromUser(userRepository.save(user));
    }

    @Override
    public UserElastic indexUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("Wrong username " + username));
        UserElastic userElastic = new UserElastic();

        BeanUtils.copyProperties(user, userElastic);
        return userElasticRepository.save(userElastic);
    }
}
