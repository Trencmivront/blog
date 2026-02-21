package com.blogs.blog.blogServices.userServices;

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

@Service
@AllArgsConstructor
public class GetAllUsersService implements Query<Void, ResponseEntity<List<User>>>{
	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<List<User>>  execute(Void v) {
		
		Optional<List<User>> user = Optional.of(userRepository.findAll());
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
			
		} catch (NoSuchElementException noSuchElementException) {
			
			noSuchElementException.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			
		}
		
	}

}
