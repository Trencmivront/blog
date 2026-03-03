package com.blogs.blog.entities.user.containers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateContainer {
	
	private final Long id;
	private final UserCreateContainer userContainer;

}
