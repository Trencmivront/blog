package com.blogs.blog.exceptions;

import com.blogs.blog.exceptions.messages.ErrorMessages;

public class NullBodyException extends RuntimeException{

	public NullBodyException() {
		super(ErrorMessages.NULL_BODY.getMessage());
	}
	
}
