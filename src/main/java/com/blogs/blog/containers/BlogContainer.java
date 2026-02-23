package com.blogs.blog.containers;

import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class BlogContainer {
	
	private String header;
	private String body;
	@Null
	private Long authorId;
	
}
