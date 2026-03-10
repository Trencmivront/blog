package com.blogs.blog.entities.blogs.containers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBlogFields {
	
//	Id of the blog
	private final Long id;
//	new body of the blog
	private final BlogContainer blogContainer;
	
	@Override
		public String toString() {
			return "Long: " + id + ", " + blogContainer;
		}
}
