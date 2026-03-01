package com.blogs.blog.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogs.blog.exceptions.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	// If we put response status in exception class itself, it won't work
	@ExceptionHandler(exception = UserNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception){
		LOGGER.error("Exception: " + UserNotFoundException.class + " thrown.");
		return new ErrorResponse(exception.getMessage());	
	}
	
	@ExceptionHandler(exception = BlogNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse blogNotFoundExceptionHandler(BlogNotFoundException exception) {
		LOGGER.warn("Exception: " + BlogNotFoundException.class + " thrown.");
		return new ErrorResponse(exception.getMessage());
	}
	
	@ExceptionHandler(exception = OwnerOfThisBlogIsSomeoneElseException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse ownerOfThisBlogIsSomeoneElseExceptionHandler(OwnerOfThisBlogIsSomeoneElseException exception) {
		LOGGER.warn("Exception: " + OwnerOfThisBlogIsSomeoneElseException.class + " thrown.");
		return new ErrorResponse(exception.getMessage());		
	}
	
	@ExceptionHandler(exception = NullBodyException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse nullBodyExceptionHandler(NullBodyException exception) {
		LOGGER.warn("Exception: " + NullBodyException.class + " thrown.");
		return new ErrorResponse(exception.getMessage());
	}
	
	@ExceptionHandler(exception = ConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse userNotValidExceptionHandler(ConstraintViolationException exception) {
		LOGGER.warn("Exception: " + ConstraintViolationException.class + " thrown.");
		return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
	}
	
	@ExceptionHandler(exception = SQLIntegrityConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse duplicateUserConstraintExceptionHandler(SQLIntegrityConstraintViolationException exception) {
		LOGGER.warn("Exception: " + SQLIntegrityConstraintViolationException.class + " thrown.");
		return new ErrorResponse("This e-mail is in use!");
	}
}
