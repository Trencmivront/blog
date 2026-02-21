package com.blogs.blog.blogServices.userServices;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.User;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserService implements Query<UserContainer, ResponseEntity<String>>{

	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<String> execute(UserContainer userContainer) {
		
		// Allowing user to make it null so nothing breaks?
		// No, nah, it creates more problem with having null body
		// Here and in front-end I will create fix for it.
		Optional<User> user;
		
		// handling the null value possibility
		try {
			
			user = Optional.of(User.builder()
					.name(userContainer.getName())
					.surname(userContainer.getSurname())
					.email(userContainer.getEmail())
					.username(userContainer.getUsername())
					.password(userContainer.getPassword())
					.build());
			
			userRepository.save(user.orElseThrow());
			
			return ResponseEntity.status(HttpStatus.CREATED).body("CREATED USER");
			
			// we will catch null value from Optional.of() method.
			// So no need to create IllegalArgumentException
		}catch (NullPointerException nullPointerException) {
			
			nullPointerException.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("USER IS NULL");
			
		}
		
	}

}
