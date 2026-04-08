package com.blogs.blog.entities.blogs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.entities.blogs.containers.BlogContainer;
import com.blogs.blog.entities.blogs.containers.BlogOwnerValidationContainer;
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
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/blogs")
public class BlogsServicesController{

	private final CreateBlogService createBlogService;
	private final DeleteBlogService deleteBlogService;
	private final UpdateBlogService updateBlogService;
	private final GetBlogService getBlogService;
	private final GetAllBlogsService getAllBlogsService;

	@PostMapping(value = "/create_blog")
	public ResponseEntity<String> createBlog(@RequestBody BlogContainer blogContainer,
			@AuthenticationPrincipal UserDetails user){

		blogContainer.setAuthorEmail(user.getUsername());

		return createBlogService.execute(blogContainer);

	}

	@DeleteMapping(value = "/delete_blog")
	public ResponseEntity<String> deleteBlog(@AuthenticationPrincipal UserDetails user, @RequestHeader Long blogId){

		return deleteBlogService.execute(new BlogOwnerValidationContainer(blogId, user.getUsername()));

	}

	// since we want to get blog container, it is being validated from Blog entity validation steps
	// during update
	@PutMapping(value = "/update_blog")
	public ResponseEntity<String> updateBlog(@AuthenticationPrincipal UserDetails user, @RequestHeader Long blogId,
			@RequestBody BlogContainer container){

		// pass in author id to container
		container.setAuthorEmail(user.getUsername());

		// send container with blog's id
		// because we don't store id in container
		return updateBlogService.execute(new UpdateBlogFields(blogId, container));

	}

	@GetMapping(value = "/get_blog")
	public ResponseEntity<BlogDTO> getBlogById(@RequestHeader Long blogId){

		return getBlogService.execute(blogId);

	}

	@GetMapping(value = "/get_blog/all")
	public ResponseEntity<List<BlogDTO>> getAllBlogs(){

		return getAllBlogsService.execute(null);

	}

}
