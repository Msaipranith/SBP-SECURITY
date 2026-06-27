package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.config.JwtService;
import com.dto.AuthRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for user authentication and JWT generation.
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Authenticates a user and returns a JWT token if successful.
     * @param authRequest the username and password
     * @return the generated JWT token
     */
    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        log.info("Received login request for username: {}", authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            log.info("Authentication successful for username: {}", authRequest.getUsername());
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            log.error("Authentication failed for username: {}", authRequest.getUsername());
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }
}
