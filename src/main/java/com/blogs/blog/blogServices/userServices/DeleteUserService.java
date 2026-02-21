package com.blogs.blog.blogServices.userServices;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.User;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteUserService implements Query<Long, String>{
	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<String> execute(Long id) {
		
		User userOptional;
		
		try {
			
			// if we don't get an error in this line, we are fine
			userOptional = Optional.of(userRepository.findById(id)).orElseThrow().get();
			
			userRepository.delete(userOptional);
			
			return ResponseEntity.ok("User deleted succesfully.");
			
		} catch (NoSuchElementException noSuchElementException) {
			
			noSuchElementException.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found.");
			
		}
		
	}
	
}
