package com.blogs.blog.entities.user.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.dto.UserDTO;
import com.blogs.blog.entities.user.repo.UserRepository;

@Service
@AllArgsConstructor
public class GetAllUsersService implements Query<Void, List<UserDTO>>{
	
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(GetAllUsersService.class);

	@Override
	public ResponseEntity<List<UserDTO>>  execute(Void i) {
		
		LOGGER.info("Executing: " + GetAllUsersService.class + " input: " + i);
		
		List<User> userOptionals = userRepository.findAll();
		
		List<UserDTO> userDTOs = userOptionals.stream().map(UserDTO::new).toList();
		
		return ResponseEntity.ok(userDTOs);
		
	}

}
