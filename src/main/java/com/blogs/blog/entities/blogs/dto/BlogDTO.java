package com.blogs.blog.entities.blogs.dto;

import java.time.LocalDateTime;

import com.blogs.blog.entities.blogs.Blogs;

import lombok.Data;

@Data
public class BlogDTO {
	
// We don't want to send author as an object, instead, we sent it's name.
	private String header;
	private String body;
	private String author;
	private LocalDateTime createDate;
	
	
	public BlogDTO(Blogs blogs) {
		
		this.header = blogs.getHeader();
		this.body = blogs.getBody();
		this.author = blogs.getAuthor().getName();
		this.createDate = blogs.getCreateDate();
		
	}
	
	
	
}
