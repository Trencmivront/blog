package com.blogs.blog.blogServices.userServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.BlogApplication;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.User;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

@Service
public class CreateUserService implements Query<UserContainer, ResponseEntity<User>>{

	
	private final UserRepository userRepository;
	
	public CreateUserService(UserRepository userRepository, BlogApplication blogApplication) {
		this.userRepository = userRepository;
	}
	
	@Override
	public ResponseEntity<User> execute(UserContainer userContainer) {
		
		User user = User.builder()
				.name(userContainer.getName())
				.surname(userContainer.getSurname())
				.email(userContainer.getEmail())
				.username(userContainer.getUsername())
				.password(userContainer.getPassword())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	

}
