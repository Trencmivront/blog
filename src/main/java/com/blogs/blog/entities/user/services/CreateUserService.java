package com.blogs.blog.entities.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogs.blog.CustomUserDetailsService;
import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.containers.UserContainer;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.impl.Query;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserService implements Query<UserContainer, String>{

	
	private final UserRepository userRepository;
	private final CustomUserDetailsService service;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserService.class);
	private final BCryptPasswordEncoder encoder;

	@Override
	public ResponseEntity<String> execute(UserContainer userContainer) {
		
		LOGGER.info("Executing: " + CreateUserService.class + " input: " + userContainer);
	
		@NotNull(message = "Password is required.")
		@NotEmpty(message = "Password is required.")
		@Pattern(regexp = "^[^\\s]+$", message = "Password cannot contain space!")
		@Size(min = 8, max = 24, message = "Password length must be between 8-24 characters.")
		String password = userContainer.getPassword();
		
		// Here and in front-end I will create fix for it.
		User user;
		
		String encodedPassword = encoder.encode(password);
		
		user = User.builder()
				.name(userContainer.getName())
				.surname(userContainer.getSurname())
				.email(userContainer.getEmail())
				.username(userContainer.getUsername())
				.password(encodedPassword)
				.build();
		
		userRepository.save(user);
//		after saving the user, we can apply role to it
		service.loadUserByUsername(user.getUsername());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED USER");
			
	}

}
