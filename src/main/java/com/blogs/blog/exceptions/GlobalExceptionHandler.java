package com.blogs.blog.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogs.blog.exceptions.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// If we put response status in exception class itself, it won't work
	@ExceptionHandler(exception = UserNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception){
		
		return new ErrorResponse(exception.getMessage());
		
	}
	
	@ExceptionHandler(exception = BlogNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse blogNotFoundExceptionHandler(BlogNotFoundException exception) {
		
		return new ErrorResponse(exception.getMessage());
		
	}
	
	@ExceptionHandler(exception = OwnerOfThisBlogIsSomeoneElseException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse ownerOfThisBlogIsSomeoneElseExceptionHandler(OwnerOfThisBlogIsSomeoneElseException exception) {
		
		return new ErrorResponse(exception.getMessage());
		
	}
	
	@ExceptionHandler(exception = NullBodyException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse nullBodyExceptionHandler(NullBodyException exception) {
		
		return new ErrorResponse(exception.getMessage());
		
	}
	
	@ExceptionHandler(exception = ConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse userNotValidExceptionHandler(ConstraintViolationException exception) {
		
		return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
		
	}
	
	@ExceptionHandler(exception = SQLIntegrityConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse duplicateUserConstraintExceptionHandler(SQLIntegrityConstraintViolationException exception) {
		
		return new ErrorResponse("This e-mail is in use!");
		
	}

}
