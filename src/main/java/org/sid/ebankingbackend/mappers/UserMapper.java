package org.sid.ebankingbackend.mappers;


import org.mapstruct.Mapper;
import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.Role;
import org.sid.ebankingbackend.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    //UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
   /*
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringListToRoleList")

    User userDtoToUser(UserDTO userDTO);

    @Named("stringListToRoleList")
    default List<Role> stringListToRoleList(List<String> roles) {
        // Implement the logic to convert List<String> to List<Role>
        // For example, you can use a stream to create Role objects based on role names
        return roles.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .collect(Collectors.toList());
    }

    UserDTO userToUserDTO(User user); // Simple

    @Mapping(target = "roles", qualifiedByName = "roleListToStringList")

    UserDTO userToUserDTOWithRoles(User user);

    @Named("roleListToStringList")
    default List<String> roleListToStringList(List<Role> roles) {
        // Implement the logic to convert List<Role> to List<String>
        // For example, you can extract role names from Role objects
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
    */
}