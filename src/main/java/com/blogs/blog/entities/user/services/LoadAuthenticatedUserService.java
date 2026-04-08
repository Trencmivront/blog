package com.blogs.blog.entities.user.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoadAuthenticatedUserService implements Query<UserDetails, com.blogs.blog.entities.user.User>{

	private final UserRepository userRepository;
	private final static Logger logger = LoggerFactory.getLogger(LoadAuthenticatedUserService.class);

	@Override
	public ResponseEntity<com.blogs.blog.entities.user.User> execute(UserDetails i) {
		logger.info("Executing: " + LoadAuthenticatedUserService.class);

		Optional<User> userOptional = userRepository.findByEmail(i.getUsername());

		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		}

		throw new UserNotFoundException();
	}

}
