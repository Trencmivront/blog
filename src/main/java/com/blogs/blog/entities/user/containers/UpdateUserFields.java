package com.blogs.blog.entities.user.containers;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserFields {
	
	private final Long id;
	private final UserContainer userContainer;

}
