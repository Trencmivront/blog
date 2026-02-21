package com.blogs.blog.containers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserContainer {
	
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;

}
