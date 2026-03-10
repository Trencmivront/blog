package com.blogs.blog.entities.blogs.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blogs;
import com.blogs.blog.entities.blogs.dto.BlogDTO;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.exceptions.BlogNotFoundException;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetBlogService implements Query<Long, BlogDTO> {
	
	private final BlogsRepository blogsRepository;
	private final static Logger LOGGER = LoggerFactory.getLogger(GetBlogService.class);

	@Override
	public ResponseEntity<BlogDTO> execute(Long id) {
		LOGGER.info("Executing: " + GetBlogService.class + " input: " + id);
		
		Optional<Blogs> blogOptional = blogsRepository.findById(id);
		
		if(!blogOptional.isPresent()) {
			
			throw new BlogNotFoundException();
			
		}
		
		// if found, return DTO version of blog
		return ResponseEntity.ok(new BlogDTO(blogOptional.get()));
	}
	
	
	

}
