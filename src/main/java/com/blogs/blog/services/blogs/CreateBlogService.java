package com.blogs.blog.services.blogs;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.BlogContainer;
import com.blogs.blog.entities.Blogs;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.BlogsRepository;
import com.blogs.blog.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateBlogService implements Query<BlogContainer, String>{
	
	private final BlogsRepository blogsRepository;
	private final UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> execute(BlogContainer blogContainer){
		
		// take user id and use it to get author?
		Blogs blogs = Blogs.builder()
				.header(blogContainer.getHeader())
				.body(blogContainer.getBody())
				.build();
		
		if(!userRepository.findById(blogContainer.getAuthorId()).isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author that is creating the blog doesn't exists.");
			
		}
		
		blogs.setAuthor(userRepository.findById(blogContainer.getAuthorId()).get());
		
		blogsRepository.save(blogs);
		
		return ResponseEntity.ok("BLOG CREATED");
	}
}
