package org.sid.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.User;
import org.sid.ebankingbackend.repositories.UserRepository;
import org.sid.ebankingbackend.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>>  getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/current")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

/*
// example E: endpoint for authenticated users which ask about his own User
    @PreAuthorize("isAuthenticated() && @securityAuthorizationService.isCurrentUser(#username)")
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("User " + username + " doesn't exist in database"));
    }




    @GetMapping("/authorities")
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    // example B: with boolean statement
    @PreAuthorize("isAnonymous() && #text.length() < 6")
    @GetMapping("/test")
    public String hello(@PathVariable String text) {
        return "Hello anonymous user!";
    }
 */

}
