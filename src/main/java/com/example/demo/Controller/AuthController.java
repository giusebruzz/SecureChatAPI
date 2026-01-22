package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dati.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.AUTH.AuthResponse;
import com.example.demo.Service.JwtService;
import com.example.demo.AUTH.LoginRequest;
import com.example.demo.AUTH.RegisterRequest;
import com.example.demo.Configuration.SecurityBeansConfig;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
	
	AuthenticationManager authenticationManager;
    JwtService jwtService;
    UserRepository userRepository;
    SecurityBeansConfig securityBeansConfig;
    UserService userService;
    
    public AuthController(AuthenticationManager authenticationManager,
    		JwtService jwtService,UserRepository userRepository, SecurityBeansConfig securityBeansConfig,UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.securityBeansConfig = securityBeansConfig;
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email già registrata");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(securityBeansConfig.passwordEncoder().encode(request.getPassword())); 
        user.setCreateAt(java.time.LocalDate.now());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );

            String jwt = jwtService.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    

}
