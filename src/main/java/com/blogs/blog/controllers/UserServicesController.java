package com.blogs.blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.containers.UpdateUserFields;
import com.blogs.blog.containers.UserContainer;
import com.blogs.blog.entities.DTO.UserDTO;
import com.blogs.blog.services.userServices.CreateUserService;
import com.blogs.blog.services.userServices.DeleteUserService;
import com.blogs.blog.services.userServices.GetAllUsersService;
import com.blogs.blog.services.userServices.GetUserService;
import com.blogs.blog.services.userServices.UpdateUserService;

import lombok.AllArgsConstructor;

@Service
@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserServicesController {
	
	private final CreateUserService createUserService;
	private final GetUserService getUserService;
	private final GetAllUsersService getAllUsersService;
	private final DeleteUserService deleteUserService;
	private final UpdateUserService updateUserService;
	
	// create service
	// we pass in a user container with only requested values
	// because we don't want to write over id or created time values
	@PostMapping(value = "/create")
	public ResponseEntity<String> postUser(@RequestBody UserContainer userContainer) {
		
		return createUserService.execute(userContainer);
		
	}
	
	// get by id service
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		
		return getUserService.execute(id);
		
	}
	
	// get all data service
	@GetMapping(value = "get/all")
	public ResponseEntity<List<UserDTO>>  getAllUsers(){
		
		return getAllUsersService.execute(null);
		
	}
	
	// delete service
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		
		return deleteUserService.execute(id);
		
	}
	
	// update service
	@PutMapping(value = "update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserContainer userContainer){
		
		return updateUserService.execute(new UpdateUserFields(id, userContainer));
		
	}
	

}







