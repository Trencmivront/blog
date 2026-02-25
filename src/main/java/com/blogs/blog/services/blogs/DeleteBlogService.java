package com.blogs.blog.services.blogs;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.BlogOwnerValidateContainer;
import com.blogs.blog.entities.Blogs;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.exceptions.OwnerOfThisBlogIsSomeoneElseException;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.BlogsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteBlogService implements Query<BlogOwnerValidateContainer, String>{

	private final BlogsRepository blogsRepository;

	@Override
	public ResponseEntity<String> execute(BlogOwnerValidateContainer blogOwnerValidateContainer){
	
		Long authorId = blogOwnerValidateContainer.getAuthorId();
		Long id = blogOwnerValidateContainer.getId();
		
		Optional<Blogs> blogOptional = blogsRepository.findById(id);
		
		// is blog exists
		if(!blogOptional.isPresent()) {
			
			throw new BlogNotFoundException();
			
		}
		
		// checking if blog belongs to the user
		if(blogOptional.get().getAuthor().getId() != authorId) {
			
			throw new OwnerOfThisBlogIsSomeoneElseException();
			
		}
		
		blogsRepository.delete(blogOptional.get());
		
		return ResponseEntity.ok("BLOG DELETED");
	}
	
}

