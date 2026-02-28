package com.blogs.blog.entities.blogs.containers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBlogFields {
	
	private final Long id;
	private final BlogContainer blogContainer;
}
