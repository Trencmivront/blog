package com.blogs.blog.entities.blogs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blogs;
import com.blogs.blog.entities.blogs.containers.UpdateBlogFields;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.exceptions.OwnerOfThisBlogIsSomeoneElseException;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateBlogService implements Query<UpdateBlogFields, String>{
	
	private final BlogsRepository blogsRepository;
	private final static Logger LOGGER = LoggerFactory.getLogger(UpdateBlogFields.class);

	@Override
	public ResponseEntity<String> execute(UpdateBlogFields fields) {
		LOGGER.info("Executing: " + UpdateBlogFields.class + " input: " + fields);
		
		Long id = fields.getId();
		
		if(!blogsRepository.findById(id).isPresent()) {
			
			throw new BlogNotFoundException();
			
		}
		
		Blogs blogs = blogsRepository.findById(id).get();
		
		String authorEmail = fields.getBlogContainer().getAuthorEmail();
		
		if(!blogs.getAuthor().getEmail().equals(authorEmail)) {
			
			throw new OwnerOfThisBlogIsSomeoneElseException();
			
		}
		
		blogs.setBody(fields.getBlogContainer().getBody());
		blogs.setHeader(fields.getBlogContainer().getHeader());
		
		blogsRepository.save(blogs);
		
		return ResponseEntity.ok("BLOG UPDATED");
	}

}
