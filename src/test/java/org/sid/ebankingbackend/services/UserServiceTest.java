package org.sid.ebankingbackend.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.Role;
import org.sid.ebankingbackend.entities.User;
import org.sid.ebankingbackend.mappers.UserMapperOld;
import org.sid.ebankingbackend.repositories.RoleRepository;
import org.sid.ebankingbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@SpringBootTest
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapperOld userMapperOld;
    @MockBean
    private RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @Test
    void deleteUser() {
        // Arrange
        Long userIdToDelete = 8L;

        // Act
        userService.deleteUser(userIdToDelete);

        // Assert
        Mockito.verify(userRepository).deleteById(userIdToDelete);
    }

    @Test
     void testGetCurrentUser() {
        // Arrange
        String username = "testUser";
        User user = new User(); // create a user as needed

        // Mock SecurityContextHolder and Authentication
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(authentication.getName()).thenReturn(username);
        securityContext.setAuthentication(authentication);

        // Mock UserRepository
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Mock UserMapperOld
        Mockito.when(userMapperOld.fromUser(user)).thenReturn(new UserDTO()); // provide a UserDTO as needed

        // Act
        UserDTO result = userService.getCurrentUser();

        // Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateUser() {
        List<Role> roles = Collections.singletonList(new Role());
        UserDTO userDto= new UserDTO();
        userDto.setUsername("testUsername");
        userDto.setPassword("123456");
        Mockito.when(roleRepository.findByNameIn(userDto.getRoles())).thenReturn(roles);
        Mockito.when(userMapperOld.fromUser(userRepository.save(new User()))).thenReturn(userDto);

        UserDTO result = userService.createUser(userDto);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testUsername", result.getUsername());

    }

}
