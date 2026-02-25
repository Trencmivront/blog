package com.blogs.blog.services.user;

import java.util.List;

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
		
		List<User> userOptionals = userRepository.findAll();
		
		List<UserDTO> userDTOs = userOptionals.stream().map(UserDTO::new).toList();
		
		return ResponseEntity.ok(userDTOs);
		
	}

}
