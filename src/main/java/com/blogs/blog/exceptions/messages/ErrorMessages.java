package com.blogs.blog.exceptions.messages;

public enum ErrorMessages {
	USER_NOT_FOUND("User not found."),
	NULL_BODY("Body is null.");
	
	private final String message;
	
	private ErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
