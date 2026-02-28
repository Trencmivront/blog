package com.blogs.blog.entities.blogs.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blogs;
import com.blogs.blog.entities.blogs.dto.BlogDTO;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.impl.Query;

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
