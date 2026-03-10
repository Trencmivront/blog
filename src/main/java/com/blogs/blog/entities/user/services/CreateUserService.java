package com.blogs.blog.entities.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.containers.UserCreateContainer;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserService implements Query<UserCreateContainer, String>{

	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserService.class);
	private final PasswordEncoder encoder;

	@Override
	public ResponseEntity<String> execute(UserCreateContainer userContainer) {
		
		LOGGER.info("Executing: " + CreateUserService.class + " input: " + userContainer);

		String password = userContainer.getPassword();
				
		String encodedPassword = encoder.encode(password);
		
		// Here and in front-end I will create fix for it
		User user = User.builder()
				.name(userContainer.getName())
				.surname(userContainer.getSurname())
				.email(userContainer.getEmail())
				.username(userContainer.getUsername())
				.password(encodedPassword)
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED USER");
			
	}

}
