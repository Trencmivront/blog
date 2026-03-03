package com.blogs.blog.exceptions.messages;

public enum ErrorMessages {
	USER_NOT_FOUND("User not found."),
	BLOG_NOT_FOUND("Blog not found."),
	NULL_BODY("Body is null."),
	FAKE_OWNER("N1g6 tis aint ur blog."),
	INCORRECT_PASSWORD("Wrong password. Try again or reset your password.");

	private final String message;
	
	private ErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
