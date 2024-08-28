package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.Users;
import com.exception.UserNotFoundException;
import com.repo.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepo empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user=empRepo.findByUsername(username);
		if (user==null) {
		System.out.println("user not found");	 
		throw new UserNotFoundException("User Not Found");
		}
		return new UserPrinicple(user);
	}

}
