package com.blogs.blog.entities.blogs.dto;

import java.time.LocalDateTime;

import com.blogs.blog.entities.blogs.Blog;

import lombok.Data;

@Data
public class BlogDTO {
	
// We don't want to send author as an object, instead, we sent it's name.
	private String header;
	private String body;
	private String author;
	private LocalDateTime createDate;
	
	
	public BlogDTO(Blog blog) {
		
		this.header = blog.getHeader();
		this.body = blog.getBody();
		this.author = blog.getAuthor().getName();
		this.createDate = blog.getCreateDate();
		
	}
	
	
	
}
