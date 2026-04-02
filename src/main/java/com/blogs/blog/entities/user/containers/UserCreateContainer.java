package com.blogs.blog.entities.user.containers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateContainer {

	private String name;
	private String surname;
	private String username;
	private String email;
//	other fields will get validated upon saving the entity
	@Pattern(regexp="^[^\\s]+$", message = "Password cannot contain space!")
	@Size(min = 8, max = 24, message = "Password length must be between 8-24 characters.")
	@NotNull(message = "Password is required.")
	@NotEmpty(message = "Password is required.")
	private String password;

}
