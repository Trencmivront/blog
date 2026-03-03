package com.blogs.blog.entities.user.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.containers.UserUpdateContainer;
import com.blogs.blog.entities.user.containers.UserCreateContainer;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.exceptions.NullBodyException;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateUserService implements Query<UserUpdateContainer, String>{
	
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdateContainer.class);
	
//	CacheEvict basically will remove cached data with the key value.
	@CacheEvict(value = "userCached", key = "#updateUserFields.getId()")
//	From the name you can get it. CachePut will update the cached data with the key.
//	Gets the returned value from method.
//	CacheEvict = remove
//	CachePut = remove and place
	@Override
//	But I can't use it since mine returns string rn.
//	@CachePut(value = "userCached", key = "#updateUserFields.getId()")
	public ResponseEntity<String> execute(UserUpdateContainer updateUserFields) {
		
		LOGGER.info("Executing: " + UpdateUserService.class + " input: " + updateUserFields.toString());
		
		Optional<User> userOptional;

		// no null value accepted
		userOptional = userRepository.findById(updateUserFields.getId());
		
		// validating id
		if(userOptional.isPresent()) {
			
			User user = userOptional.get();
			// validating body
			Optional<UserCreateContainer> userContainerOptional = Optional.ofNullable(updateUserFields.getUserContainer());
			
			// exit if there is no userContainer
			if(!userContainerOptional.isPresent()) {throw new NullBodyException();}
			
			UserCreateContainer userContainer = userContainerOptional.get();
			
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
