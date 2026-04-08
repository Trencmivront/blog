package com.blogs.blog.entities.blogs.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.blogs.Blog;
import com.blogs.blog.entities.blogs.dto.BlogDTO;
import com.blogs.blog.entities.blogs.repo.BlogsRepository;
import com.blogs.blog.interfcs.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetAllBlogsService implements Query<Void, List<BlogDTO>>{
	
	private final BlogsRepository blogsRepository;
	private final static Logger LOGGER = LoggerFactory.getLogger(GetAllBlogsService.class);
	
	@Override
	public ResponseEntity<List<BlogDTO>> execute(Void i) {
		LOGGER.info("Executing: " + GetAllBlogsService.class + " input: " + i);
		
		List<Blog> blog = blogsRepository.findAll();
		
		List<BlogDTO> blogDTOs = blog.stream().map(BlogDTO::new).toList();
		
		return ResponseEntity.ok(blogDTOs);
	}

}
