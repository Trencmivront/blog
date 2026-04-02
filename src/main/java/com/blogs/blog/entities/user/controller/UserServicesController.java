package com.blogs.blog.entities.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.entities.user.containers.UserCreateContainer;
import com.blogs.blog.entities.user.containers.UserSignInContainer;
import com.blogs.blog.entities.user.containers.UserUpdateContainer;
import com.blogs.blog.entities.user.dto.UserDTO;
import com.blogs.blog.entities.user.services.CreateUserService;
import com.blogs.blog.entities.user.services.DeleteUserService;
import com.blogs.blog.entities.user.services.GetAllUsersService;
import com.blogs.blog.entities.user.services.GetUserService;
import com.blogs.blog.entities.user.services.UpdateUserService;
import com.blogs.blog.entities.user.services.UserLogInService;
import com.blogs.blog.jwt.JwtUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


// allowing implementation of classes be auto detected by class path scanning
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/user")
public class UserServicesController {
	AuthenticationManager manager;
	// let beans handle creating these services
	private final CreateUserService createUserService;
	private final GetUserService getUserService;
	private final GetAllUsersService getAllUsersService;
	private final DeleteUserService deleteUserService;
	private final UpdateUserService updateUserService;
	private final UserLogInService userLogInService;
	// create service
	// we pass in a user container with only requested values
	// because we don't want to write over id or created time values
	@PostMapping(value = "/create")
	public ResponseEntity<String> postUser(@RequestBody @Valid UserCreateContainer container) {

		return createUserService.execute(container);

	}

	// get by id service
	@GetMapping(value = "/get")
	public ResponseEntity<UserDTO> getUserById(@RequestHeader Long id) {

		return getUserService.execute(id);

	}

	// get all data service
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<UserDTO>>  getAllUsers(){

		return getAllUsersService.execute(null);

	}

	// delete service
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteUser(@RequestHeader Long id){

		return deleteUserService.execute(id);

	}

	// update service
	@PutMapping(value = "/update")
	public ResponseEntity<String> updateUser(@RequestHeader Long id, @RequestBody UserCreateContainer container){

		return updateUserService.execute(new UserUpdateContainer(id, container));

	}

	@GetMapping("/login")
	public ResponseEntity<String> signInUser(
	@RequestBody UserSignInContainer container) {
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(container.getEmail(),
						container.getPassword());

		Authentication authentication = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());

		return ResponseEntity.ok(jwtToken);
	}

}







