package com.blogs.blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.containers.BlogContainer;
import com.blogs.blog.containers.BlogOwnerValidateContainer;
import com.blogs.blog.containers.UpdateBlogFields;
import com.blogs.blog.services.blogs.CreateBlogService;
import com.blogs.blog.services.blogs.DeleteBlogService;
import com.blogs.blog.services.blogs.UpdateBlogService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/blogs")
public class BlogsServicesController{
	
	private final CreateBlogService createBlogService;
	private final DeleteBlogService deleteBlogService;
	private final UpdateBlogService updateBlogService;

	@PostMapping(value = "/user/{authorId}/create_blog")
	public ResponseEntity<String> createBlog(@RequestBody BlogContainer blogContainer, @PathVariable Long authorId){
		
		blogContainer.setAuthorId(authorId);
		
		return createBlogService.execute(blogContainer);
		
	}
	
	@DeleteMapping(value = "/user/{authorId}/delete_blog/{id}")
	public ResponseEntity<String> deleteBlog(@PathVariable Long authorId, @PathVariable Long id){
		
		return deleteBlogService.execute(new BlogOwnerValidateContainer(id, authorId));
		
	}
	
	// since we want to get blog container, it is being validated from Blogs entity validation steps
	// during update
	@PutMapping()
	public ResponseEntity<String> updateBlog(@PathVariable Long authorId, @PathVariable Long id,
			@RequestBody BlogContainer container){
		
		// pass in author id to container
		container.setAuthorId(authorId);
		
		// send container with blog's id
		// because we don't store id in container
		return updateBlogService.execute(new UpdateBlogFields(id, container));
		
	}
	
}
