package com.blogs.blog.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.blogServices.userServices.CreateUserService;
import com.blogs.blog.containers.UserContainer;


@Service
@RestController
@RequestMapping(value = "/user")
public class UserServicesController {
	
	private final CreateUserService createUserService;

	public UserServicesController(CreateUserService putService) {
		this.createUserService = putService;
	}
	
	
	
	@PostMapping(value = "/create")
	public HttpStatusCode postUser(@RequestBody UserContainer userContainer) {
		
		System.out.println(createUserService.execute(userContainer).getStatusCode());
		
		return createUserService.execute(userContainer).getStatusCode();
		
	}
	

}
