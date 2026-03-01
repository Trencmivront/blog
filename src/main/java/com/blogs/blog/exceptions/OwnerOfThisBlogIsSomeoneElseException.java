package com.blogs.blog.exceptions;

import com.blogs.blog.exceptions.messages.ErrorMessages;

@SuppressWarnings("serial")
public class OwnerOfThisBlogIsSomeoneElseException extends RuntimeException{
	public OwnerOfThisBlogIsSomeoneElseException() {
		super(ErrorMessages.FAKE_OWNER.getMessage());
	}	
}
