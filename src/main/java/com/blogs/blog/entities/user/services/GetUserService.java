package com.blogs.blog.entities.user.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.dto.UserDTO;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetUserService implements Query<Long, UserDTO>{
	
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(GetUserService.class);
	
//	We want to cache the outcome because it will be same for most of the time
	@Cacheable(value = "userCached")
	@Override
	public ResponseEntity<UserDTO> execute(Long id) {
		
		LOGGER.info("Executing: " + GetUserService.class + " input: " + id);
		
		// user can be null
		Optional<User> userOptional;
		
		userOptional = userRepository.findById(id);
		
		if(userOptional.isPresent()) {
			
			// Using UserDTO, we only get values we need.
			// Then wrap it in ResponseEntity and send
			return ResponseEntity.ok(new UserDTO(userOptional.get()));
			
		}

		throw new UserNotFoundException();
	}
}









