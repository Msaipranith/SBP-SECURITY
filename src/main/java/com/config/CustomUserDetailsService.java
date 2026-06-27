package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.repo.UsersRepo;
import lombok.extern.slf4j.Slf4j;

/**
 * Service responsible for fetching user details from the database
 * to be utilized by Spring Security during authentication.
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user details for username: {}", username);
        return usersRepo.findByUsername(username).map(user -> {
            log.debug("Successfully loaded user details for: {}", username);
            return new CustomUserDetails(user);
        }).orElseThrow(() -> {
            log.error("User not found in database: {}", username);
            return new UsernameNotFoundException("User not found with username: " + username);
        });
    }
}
