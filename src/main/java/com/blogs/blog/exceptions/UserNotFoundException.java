package com.blogs.blog.exceptions;

import com.blogs.blog.exceptions.messages.ErrorMessages;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UserNotFoundException  extends RuntimeException{
	
	private final static Logger logger = LoggerFactory.getLogger(UserNotFoundException.class);
	
	public UserNotFoundException() {
		
		logger.error("Exception: " + UserNotFoundException.class + " thrown.");
		
		super(ErrorMessages.USER_NOT_FOUND.getMessage());
	}


}
