package com.blogs.blog.entities.user.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteUserService implements Query<Long, String>{
	
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserService.class);

	@Override
	public ResponseEntity<String> execute(Long id) {
		
		LOGGER.info("Executing: " + DeleteUserService.class + " input: " + id);
		
		Optional<User> userOptional;
		
		// if we don't get an error in this line, we are fine
		userOptional = userRepository.findById(id);
		
		if(userOptional.isPresent()) {
			
			userRepository.delete(userOptional.get());
			
			return ResponseEntity.ok("User deleted succesfully.");
			
		}

		throw new UserNotFoundException();
		
	}
	
}