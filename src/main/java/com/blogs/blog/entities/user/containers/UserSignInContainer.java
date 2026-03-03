package com.blogs.blog.entities.user.containers;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignInContainer {
	
	private String email;
	private String password;
}
