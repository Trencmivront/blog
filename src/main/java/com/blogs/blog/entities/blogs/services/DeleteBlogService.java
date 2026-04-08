package com.blogs.blog.entities.blogs.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blog;
import com.blogs.blog.entities.blogs.containers.BlogOwnerValidationContainer;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.exceptions.OwnerOfThisBlogIsSomeoneElseException;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteBlogService implements Query<BlogOwnerValidationContainer, String>{

	private final BlogsRepository blogsRepository;
	private final static Logger LOGGER = LoggerFactory.getLogger(DeleteBlogService.class);

	@Override
	public ResponseEntity<String> execute(BlogOwnerValidationContainer blogOwnerValidateContainer){
		LOGGER.info("Executing: " + DeleteBlogService.class + " input: " + blogOwnerValidateContainer);
		
		String authorEmail = blogOwnerValidateContainer.getAuthorEmail();
		Long id = blogOwnerValidateContainer.getId();
		
		Optional<Blog> blogOptional = blogsRepository.findById(id);
		
		// is blog exists
		if(!blogOptional.isPresent()) {
			
			throw new BlogNotFoundException();
			
		}
		
		// checking if blog belongs to the user
		if(!blogOptional.get().getAuthor().getEmail().equals(authorEmail)) {
			
			throw new OwnerOfThisBlogIsSomeoneElseException();
			
		}
		
		blogsRepository.delete(blogOptional.get());
		
		return ResponseEntity.ok("BLOG DELETED");
	}
	
}

