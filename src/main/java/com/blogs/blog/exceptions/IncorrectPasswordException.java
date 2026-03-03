package com.blogs.blog.exceptions;

import com.blogs.blog.exceptions.messages.ErrorMessages;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends RuntimeException{
	
	public IncorrectPasswordException() {
		super(ErrorMessages.INCORRECT_PASSWORD.getMessage());
	}

}
