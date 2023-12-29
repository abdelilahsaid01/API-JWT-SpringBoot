package org.sid.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import org.sid.ebankingbackend.dtos.UserDTO;
import org.sid.ebankingbackend.entities.UserElastic;
import org.sid.ebankingbackend.services.Impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void getAllUsers(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) { // you can use GlobalExceptionHandler instead of BindingResult
        if (bindingResult.hasErrors()) {
            String errorMessage = "Validation error: " + bindingResult.getAllErrors();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        userDTO.setId(id);

        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @GetMapping("/index/{username}")
    public UserElastic indexUser(@PathVariable String username) {
       return userService.indexUser(username);
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
