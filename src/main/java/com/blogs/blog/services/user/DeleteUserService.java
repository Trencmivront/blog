package com.blogs.blog.services.user;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.User;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteUserService implements Query<Long, String>{
	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<String> execute(Long id) {
		
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