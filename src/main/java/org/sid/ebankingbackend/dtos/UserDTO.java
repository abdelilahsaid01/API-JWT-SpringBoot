package org.sid.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    @NotNull
    private String username;
    private String password;
    @Email(message = "Email is required")
    private String email;
    private List<String> roles;
}
