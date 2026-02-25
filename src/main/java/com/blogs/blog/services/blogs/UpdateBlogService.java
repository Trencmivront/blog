package com.blogs.blog.services.blogs;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.UpdateBlogFields;
import com.blogs.blog.entities.Blogs;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.exceptions.OwnerOfThisBlogIsSomeoneElseException;
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
			
			throw new BlogNotFoundException();
			
		}
		
		Blogs blogs = blogsRepository.findById(id).get();
		
		Long authorId = fields.getBlogContainer().getAuthorId();
		
		if(blogs.getAuthor().getId() != authorId) {
			
			throw new OwnerOfThisBlogIsSomeoneElseException();
			
		}
		
		blogs.setBody(fields.getBlogContainer().getBody());
		blogs.setHeader(fields.getBlogContainer().getHeader());
		
		blogsRepository.save(blogs);
		
		return ResponseEntity.ok("BLOG UPDATED");
	}

}
