package com.blogs.blog.services.userServices;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.User;
import com.blogs.blog.entities.DTO.UserDTO;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetUserService implements Query<Long, UserDTO>{
	
	private final UserRepository userRepository;
	
	@Override
	public ResponseEntity<UserDTO> execute(Long id) {
		
		// user can be null
		Optional<User> userOptional;
		
		try {
			
			userOptional = userRepository.findById(id);
			
		} catch (NoSuchElementException noSuchElementException) {
			
			System.out.println("No Such Element Exception: Element not found");
			return ResponseEntity.noContent().build();
			
		}catch (IllegalArgumentException illegalArgumentException) {
			
			System.out.println("Illegal Argument Exception: Id must be a type Long.");
			return ResponseEntity.noContent().build();
		}
		
		// Using UserDTO, we only get values we need.
		// Then wrap it in ResponseEntity and send
		return ResponseEntity.ok(new UserDTO(userOptional.get()));
	}
}









