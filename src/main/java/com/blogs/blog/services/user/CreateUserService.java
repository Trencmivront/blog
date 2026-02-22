package com.blogs.blog.services.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.User;
import com.blogs.blog.exceptions.NullBodyException;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserService implements Query<UserContainer, String>{

	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<String> execute(UserContainer userContainer) {
		
		// checking if the body is null
		if(userContainer == null) {
			throw new NullBodyException();
		}
		
		// Here and in front-end I will create fix for it.
		User user;
		
		user = User.builder()
				.name(userContainer.getName())
				.surname(userContainer.getSurname())
				.email(userContainer.getEmail())
				.username(userContainer.getUsername())
				.password(userContainer.getPassword())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED USER");
			
	}

}
