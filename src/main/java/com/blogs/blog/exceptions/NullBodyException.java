package com.blogs.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogs.blog.exceptions.messages.ErrorMessages;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullBodyException extends RuntimeException{

	public NullBodyException() {
		super(ErrorMessages.NULL_BODY.getMessage());
	}
	
}
