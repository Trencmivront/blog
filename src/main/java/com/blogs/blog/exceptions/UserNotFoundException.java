package com.blogs.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogs.blog.exceptions.messages.ErrorMessages;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException  extends RuntimeException{
	
	public UserNotFoundException() {
		super(ErrorMessages.USER_NOT_FOUND.getMessage());
	}


}
