package com.blogs.blog.exceptions.response;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private final String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

}
