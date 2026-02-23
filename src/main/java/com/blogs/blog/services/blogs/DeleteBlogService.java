package com.blogs.blog.services.blogs;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.containers.BlogOwnerValidateContainer;
import com.blogs.blog.entities.Blogs;
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
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found.");
			
		}
		
		// checking if blog belongs to the user
		if(blogOptional.get().getAuthor().getId() != authorId) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("N1g6 tis aint ur blog.");
			
		}
		
		blogsRepository.delete(blogOptional.get());
		
		return ResponseEntity.ok("BLOG DELETED");
	}
	
}

