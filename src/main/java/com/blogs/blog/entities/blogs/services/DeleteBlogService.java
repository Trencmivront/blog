package com.blogs.blog.entities.blogs.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blogs;
import com.blogs.blog.entities.blogs.containers.BlogOwnerValidationContainer;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.exceptions.OwnerOfThisBlogIsSomeoneElseException;
import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteBlogService implements Query<BlogOwnerValidationContainer, String>{

	private final BlogsRepository blogsRepository;

	@Override
	public ResponseEntity<String> execute(BlogOwnerValidationContainer blogOwnerValidateContainer){
	
		String authorUsername = blogOwnerValidateContainer.getAuthorUsername();
		Long id = blogOwnerValidateContainer.getId();
		
		Optional<Blogs> blogOptional = blogsRepository.findById(id);
		
		// is blog exists
		if(!blogOptional.isPresent()) {
			
			throw new BlogNotFoundException();
			
		}
		
		// checking if blog belongs to the user
		if(blogOptional.get().getAuthor().getEmail().equals(authorUsername)) {
			
			throw new OwnerOfThisBlogIsSomeoneElseException();
			
		}
		
		blogsRepository.delete(blogOptional.get());
		
		return ResponseEntity.ok("BLOG DELETED");
	}
	
}

