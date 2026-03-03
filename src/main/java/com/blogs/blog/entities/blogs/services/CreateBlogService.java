package com.blogs.blog.entities.blogs.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blogs;
import com.blogs.blog.entities.blogs.containers.BlogContainer;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.exceptions.UserNotFoundException;
import com.blogs.blog.impl.Query;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateBlogService implements Query<BlogContainer, String>{
	
	private final BlogsRepository blogsRepository;
	private final UserRepository userRepository;
	
	private final static Logger logger = LoggerFactory.getLogger(CreateBlogService.class);
	
	@Override
	public ResponseEntity<String> execute(BlogContainer blogContainer){
		
		logger.info("Executing: " + CreateBlogService.class + " input: " + blogContainer);
		
		// take user id and use it to get author?
		Blogs blogs = Blogs.builder()
				.header(blogContainer.getHeader())
				.body(blogContainer.getBody())
				.build();
		
		if(!userRepository.findByUsername(blogContainer.getAuthorUsername()).isPresent()) {
		
			throw new UserNotFoundException();
			
		}
		
		blogs.setAuthor(userRepository.findByUsername(blogContainer.getAuthorUsername()).get());
		
		blogsRepository.save(blogs);
		
		return ResponseEntity.ok("BLOG CREATED");
	}
}
