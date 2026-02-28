package com.blogs.blog.entities.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.containers.UserContainer;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserService implements Query<UserContainer, String>{

	
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserService.class);

	@Override
	public ResponseEntity<String> execute(UserContainer userContainer) {
		
		LOGGER.info("Executing: " + CreateUserService.class + " input: " + userContainer);
		
		// Here and in front-end I will create fix for it.
		User user;
		
		user = User.builder()
				.name(userContainer.getName())
				.surname(userContainer.getSurname())
				.email(userContainer.getEmail())
				.username(userContainer.getUsername())
				.password(userContainer.getPassword())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED USER");
			
	}

}
