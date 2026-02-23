package com.blogs.blog.services.blogs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.UpdateBlogFields;
import com.blogs.blog.entities.Blogs;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.BlogsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateBlogService implements Query<UpdateBlogFields, String>{
	
	private final BlogsRepository blogsRepository;

	@Override
	public ResponseEntity<String> execute(UpdateBlogFields fields) {
		
		Long id = fields.getId();
		
		if(!blogsRepository.findById(id).isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found.");
			
		}
		
		Blogs blogs = blogsRepository.findById(id).get();
		
		Long authorId = fields.getBlogContainer().getAuthorId();
		
		if(blogs.getAuthor().getId() != authorId) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("N1g6 tis aint ur blog.");
			
		}
		
		blogs.setBody(fields.getBlogContainer().getBody());
		blogs.setHeader(fields.getBlogContainer().getHeader());
		
		return ResponseEntity.ok("BLOG UPDATED");
	}

}
