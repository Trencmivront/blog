package com.blogs.blog.services.blogsServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.BlogContainer;
import com.blogs.blog.entities.Blogs;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.BlogsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateBlogService implements Query<BlogContainer, String>{
	
	private final BlogsRepository blogsRepository;
	
	@Override
	public ResponseEntity<String> execute(BlogContainer blogContainer) {
		
		if(blogContainer == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Value can't be null");
		}
		
		Blogs blogs = Blogs.builder()
				.header(blogContainer.getHeader())
				.body(blogContainer.getBody())
				.authorId(blogContainer.getAuthorId())
				.build();
		
		blogsRepository.save(blogs);
		
		return ResponseEntity.ok("CREATED BLOG");
	}
}
