package com.blogs.blog.services.user;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.UpdateUserFields;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.User;
import com.blogs.blog.exceptions.NullBodyException;
import com.blogs.blog.exceptions.UserNotFoundException;
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

		// no null value accepted
		userOptional = userRepository.findById(updateUserFields.getId());
		
		// validating id
		if(userOptional.isPresent()) {
			
			User user = userOptional.get();
			// validating body
			Optional<UserContainer> userContainerOptional = Optional.ofNullable(updateUserFields.getUserContainer());
			
			// exit if there is no userContainer
			if(!userContainerOptional.isPresent()) {throw new NullBodyException();}
			
			UserContainer userContainer = userContainerOptional.get();
			
			// updating them one by one because I want to keep id and createdAt same
			// since createdAt is signed as creation of element I cannot assign to it
			user.setEmail(userContainer.getEmail());
			user.setName(userContainer.getName());
			user.setSurname(userContainer.getSurname());
			user.setPassword(userContainer.getPassword());
			user.setUsername(userContainer.getUsername());
			
			// since it already has the id, it will be overwritten on itself
			userRepository.save(user);
			
			return ResponseEntity.ok("Updated user");
		}

		throw new UserNotFoundException();
		
	}

}
