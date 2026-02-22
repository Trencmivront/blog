package com.blogs.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogs.blog.exceptions.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = UserNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception){
		
		return new ErrorResponse(exception.getMessage());
		
	}
	
	@ExceptionHandler(exception = NullBodyException.class)
	public ErrorResponse nullBodyExceptionHandler(NullBodyException exception) {
		
		return new ErrorResponse(exception.getMessage());
		
	}

}
