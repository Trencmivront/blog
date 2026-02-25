package com.blogs.blog.services.blogs;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.Blogs;
import com.blogs.blog.entities.DTO.BlogDTO;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.BlogsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetBlogService implements Query<Long, BlogDTO> {
	
	private final BlogsRepository blogsRepository;

	@Override
	public ResponseEntity<BlogDTO> execute(Long id) {
		
		Optional<Blogs> blogOptional = blogsRepository.findById(id);
		
		if(!blogOptional.isPresent()) {
			
			throw new BlogNotFoundException();
			
		}
		
		// if found, return DTO version of blog
		return ResponseEntity.ok(new BlogDTO(blogOptional.get()));
	}
	
	
	

}
