package com.blogs.blog.entities.user.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.blogs.blog.entities.user.containers.UserLogInContainer;
import com.blogs.blog.interfcs.Query;
import com.blogs.blog.jwt.JwtUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserLogInService implements Query<UserLogInContainer, String>{

	AuthenticationManager manager;

	private final static Logger LOGGER = LoggerFactory.getLogger(UserLogInService.class);

	@Override
	public ResponseEntity<String> execute(UserLogInContainer container) {
		LOGGER.info("Executing: " + UserLogInService.class);

		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(container.getEmail(),
						container.getPassword());

		Authentication authentication = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());

		return ResponseEntity.ok(jwtToken);
	}

}
