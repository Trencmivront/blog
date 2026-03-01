package com.blogs.blog.exceptions;

import com.blogs.blog.exceptions.messages.ErrorMessages;

@SuppressWarnings("serial")
public class BlogNotFoundException extends RuntimeException{
	public BlogNotFoundException() {
		super(ErrorMessages.BLOG_NOT_FOUND.getMessage());
	}

}