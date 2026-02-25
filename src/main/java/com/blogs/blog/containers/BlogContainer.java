package com.blogs.blog.containers;

import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class BlogContainer {
	
	private String header;
	private String body;
	// we say that this is not going to be added manually
	// but will taken from URL
	@Null
	private Long authorId;
	
}
