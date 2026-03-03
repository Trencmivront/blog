package com.blogs.blog.entities.user.dto;

import com.blogs.blog.entities.user.User;

import lombok.Data;

// Data Transfer Object (DTO) contains the data we will send
// through API. This data may or may not contain all values of
// original table. This way we can avoid sending unwanted or
// vulnerable data, protecting database
@Data
public class UserDTO {
	
	private Long id;
	private String name;
	private String surname;
	private String username;
	private String email;
	
	// get values from user
	public UserDTO(User user) {
		
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.username = user.getUsername();
		
	}

}
