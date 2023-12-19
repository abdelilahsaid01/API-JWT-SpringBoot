package org.sid.ebankingbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private List<String> roles;
}
