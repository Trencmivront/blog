package com.blogs.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.jwt.JwtAuthenticationFilter;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

	private final UserRepository userRepository;

	public SecurityConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	PasswordEncoder encoder() {

		return new BCryptPasswordEncoder();

	}
	@Bean
	AuthenticationManager authentication(HttpSecurity security) {

		return security.getSharedObject(AuthenticationManagerBuilder.class).build();

	}

	@Bean
	SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> {
					auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
					.requestMatchers("/user/login").permitAll()
					.requestMatchers("/logIn").permitAll()
					.requestMatchers("/user/create").permitAll()
					.requestMatchers("/user/get").permitAll()
					.requestMatchers("/user/load").hasRole("USER")
					.requestMatchers("/register").permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/blogs/get_blog/all").permitAll()
					.requestMatchers("/blogs/delete_blog").hasRole("USER")
					.requestMatchers("/blogs/create_blog").hasRole("USER")
					.anyRequest().authenticated();
				})
				.addFilterBefore(
						jwtAuthenticationFilter(),
						UsernamePasswordAuthenticationFilter.class)
				.build();

	}

	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(userRepository);
	}

}
