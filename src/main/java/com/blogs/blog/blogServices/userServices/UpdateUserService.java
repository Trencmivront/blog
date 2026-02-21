package com.blogs.blog.blogServices.userServices;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.UpdateUserFields;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.User;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateUserService implements Query<UpdateUserFields, String>{
	
	private final UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> execute(UpdateUserFields updateUserFields) {
		
		Optional<User> userOptional;
		
		try {
			// no null value accepted
			userOptional = userRepository.findById(updateUserFields.getId());
			// validating id
			User user = userOptional.orElseThrow();
			// validating body
			UserContainer userContainer = Optional.of(updateUserFields.getUserContainer()).orElseThrow();			
			
			user.setEmail(userContainer.getEmail());
			user.setName(userContainer.getName());
			user.setSurname(userContainer.getSurname());
			user.setPassword(userContainer.getPassword());
			user.setUsername(userContainer.getUsername());
			
			// since it already has the id, it will be overwritten on itself
			userRepository.save(user);
			
			return ResponseEntity.ok("Updated user");
			
		} catch (NoSuchElementException noSuchElementException) {
			
			noSuchElementException.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found");
			
		}
		
	}

}
