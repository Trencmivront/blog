package com.blogs.blog.entities.blogs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.entities.blogs.containers.BlogContainer;
import com.blogs.blog.entities.blogs.containers.BlogOwnerValidateContainer;
import com.blogs.blog.entities.blogs.containers.UpdateBlogFields;
import com.blogs.blog.entities.blogs.dto.BlogDTO;
import com.blogs.blog.entities.blogs.services.CreateBlogService;
import com.blogs.blog.entities.blogs.services.DeleteBlogService;
import com.blogs.blog.entities.blogs.services.GetAllBlogsService;
import com.blogs.blog.entities.blogs.services.GetBlogService;
import com.blogs.blog.entities.blogs.services.UpdateBlogService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/blogs")
public class BlogsServicesController{
	
	private final CreateBlogService createBlogService;
	private final DeleteBlogService deleteBlogService;
	private final UpdateBlogService updateBlogService;
	private final GetBlogService getBlogService;
	private final GetAllBlogsService getAllBlogsService;

	@PostMapping(value = "/create_blog")
	public ResponseEntity<String> createBlog(@RequestBody BlogContainer blogContainer, @RequestHeader Long authorId){
		
		blogContainer.setAuthorId(authorId);
		
		return createBlogService.execute(blogContainer);
		
	}
	
	@DeleteMapping(value = "/user/delete_blog/{id}")
	public ResponseEntity<String> deleteBlog(@PathVariable Long authorId, @PathVariable Long id){
		
		return deleteBlogService.execute(new BlogOwnerValidateContainer(id, authorId));
		
	}
	
	// since we want to get blog container, it is being validated from Blogs entity validation steps
	// during update
	@PutMapping(value = "/user/update_blog/{id}")
	public ResponseEntity<String> updateBlog(@RequestHeader Long authorId, @PathVariable Long id,
			@RequestBody BlogContainer container){
		
		// pass in author id to container
		container.setAuthorId(authorId);
		
		// send container with blog's id
		// because we don't store id in container
		return updateBlogService.execute(new UpdateBlogFields(id, container));
		
	}
	
	@GetMapping(value = "get_blog/{id}")
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id){
		
		return getBlogService.execute(id);
		
	}
	
	@GetMapping(value = "get_blog/all")
	public ResponseEntity<List<BlogDTO>> getAllBlogs(){
		
		return getAllBlogsService.execute(null);
		
	}
	
}
