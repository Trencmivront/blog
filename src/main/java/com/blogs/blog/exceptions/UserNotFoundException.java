package com.blogs.blog.exceptions;

import com.blogs.blog.exceptions.messages.ErrorMessages;

public class UserNotFoundException  extends RuntimeException{
	
	public UserNotFoundException() {
		super(ErrorMessages.USER_NOT_FOUND.getMessage());
	}


}
