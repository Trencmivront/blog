package com.blogs.blog.entities.user.containers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignInContainer {
	
	@NotNull(message = "email can't be null!")
	@NotEmpty(message = "email can't be empty!")
	@Email(message = "Please write a valid email address!")
	private String email;
	
	@NotNull(message = "Password can't be null!")
	@NotEmpty(message = "Password can't be empty!")
	private String password;
}
