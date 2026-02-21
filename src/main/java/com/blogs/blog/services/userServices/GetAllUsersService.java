package com.blogs.blog.services.userServices;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

import com.blogs.blog.entities.User;
import com.blogs.blog.entities.DTO.UserDTO;

@Service
@AllArgsConstructor
public class GetAllUsersService implements Query<Void, List<UserDTO>>{
	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<List<UserDTO>>  execute(Void i) {
		
		Optional<List<User>> userOptionals;
		List<UserDTO> userDTOs;
		
		try {
			
			userOptionals = Optional.of(userRepository.findAll());
			// if no user found, catch the exception and give No-Content
			userDTOs = userOptionals.orElseThrow().stream().map(UserDTO::new).toList();
			
			return ResponseEntity.ok(userDTOs);
			
		} catch (NoSuchElementException noSuchElementException) {
			
			noSuchElementException.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			
		}
		
	}

}
