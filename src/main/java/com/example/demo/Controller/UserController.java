package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dati.User;
import com.example.demo.Dati.UserStatus;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{email}/status")
    public ResponseEntity<UserStatus> getStatus(@PathVariable String email) throws UserNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user not found "+email));

        return ResponseEntity.ok(user.getStatus());
    }
}
