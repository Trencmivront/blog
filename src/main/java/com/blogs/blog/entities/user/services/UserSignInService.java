package com.blogs.blog.entities.user.services;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogs.blog.config.CustomUserDetailsService;
import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.containers.UserSignInContainer;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.exceptions.IncorrectPasswordException;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserSignInService implements Query<UserSignInContainer, String>{
	
	private final CustomUserDetailsService service;
	private final PasswordEncoder encoder;
	private final UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> execute(UserSignInContainer container) {
		
		User user;
		
		try {
//			we will find user by it's email
			user = userRepository.findByEmail(container.getEmail()).orElseThrow();
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException();
		}
		
		if(!encoder.matches(container.getPassword(), user.getPassword())) {
			throw new IncorrectPasswordException();
		}
		
//		if user exists, we can apply role to it
		service.loadUserByUsername(user.getEmail());
		
		return ResponseEntity.ok("USER SIGNED IN");
	}

}
