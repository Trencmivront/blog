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

import com.blogs.blog.jwt.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
	
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
					
					auth.requestMatchers("/user/login").permitAll();
					auth.requestMatchers("/user/create").permitAll();
					auth.requestMatchers("/blogs/get_blog/all");
					auth.requestMatchers("/blogs/delete_blog").hasRole("USER");
					auth.requestMatchers("/blogs/create_blog").hasRole("USER");
					auth.anyRequest().authenticated();
				})
				.addFilterBefore(
						jwtAuthenticationFilter(),
						UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

}
