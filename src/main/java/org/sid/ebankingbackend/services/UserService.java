package org.sid.ebankingbackend.services;

import org.sid.ebankingbackend.dtos.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getCurrentUser();
    List<UserDTO> getUsers();

    UserDTO createUser(UserDTO userDTO);
}
