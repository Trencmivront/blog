package com.blogs.blog.services.blogs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogs.blog.entities.Blogs;
import com.blogs.blog.entities.DTO.BlogDTO;
import com.blogs.blog.impl.Query;
import com.blogs.blog.repos.BlogsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetAllBlogsService implements Query<Void, List<BlogDTO>>{
	
	private final BlogsRepository blogsRepository;
	
	@Override
	public ResponseEntity<List<BlogDTO>> execute(Void i) {
		
		List<Blogs> blogs = blogsRepository.findAll();
		
		List<BlogDTO> blogDTOs = blogs.stream().map(BlogDTO::new).toList();
		
		return ResponseEntity.ok(blogDTOs);
	}

}
