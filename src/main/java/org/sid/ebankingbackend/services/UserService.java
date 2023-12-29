package org.sid.ebankingbackend.services;

import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.UserElastic;

import java.util.List;

public interface UserService {
    void deleteUser(Long id);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO getCurrentUser();
    List<UserDTO> getUsers();

    UserDTO createUser(UserDTO userDTO);

    UserElastic indexUser(String username);
}
