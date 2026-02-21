package com.blogs.blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.blogServices.userServices.CreateUserService;
import com.blogs.blog.blogServices.userServices.GetUserService;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.UserDTO;

import lombok.AllArgsConstructor;

@Service
@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserServicesController {
	
	private final CreateUserService createUserService;
	private final GetUserService getUserService;
	
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> postUser(@RequestBody UserContainer userContainer) {
		
		return createUserService.execute(userContainer);
		
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		
		return getUserService.execute(id);
		
	}
	
	@GetMapping(value = "get/all")
	

}







