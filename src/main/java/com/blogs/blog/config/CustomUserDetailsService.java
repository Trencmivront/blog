package com.blogs.blog.config;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.user.repo.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, NoSuchElementException{
		com.blogs.blog.entities.user.User user = userRepository.findByEmail(email).get();
		
//		email of the user will be username of the user
		return User.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}	
}
