package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(customizer -> customizer.disable()) // to disable csrf(no need of csrf token and not secured)
				// .formLogin(t -> t.disable()) // to disable form login(doesn't ask for form while hit any request)
				// .formLogin(Customizer.withDefaults()) // uses form login with default properties
				.httpBasic(Customizer.withDefaults()) // uses postman with properties
				.authorizeHttpRequests(request -> request.anyRequest().authenticated()) // any request is
																						// authenticated(user needs to
																						// send credentials based on
																						// authentication)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user1=User
//				.withDefaultPasswordEncoder()
//				.username("sunny")
//				.password("Sunny")
//				.roles("USER").build();
//		
//		UserDetails user2=User
//				.withDefaultPasswordEncoder()
//				.username("pranith")
//				.password("Pranith")
//				.roles("USER").build();
//		
//		
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//		
//	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider provider=	new DaoAuthenticationProvider();
	provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	provider.setUserDetailsService(userDetailsService);
	
	return provider;
	}


}
