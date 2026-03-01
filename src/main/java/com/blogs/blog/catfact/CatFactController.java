package com.blogs.blog.catfact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CatFactController {
	
	private final GetCatFactService getCatFactService;
	
	@GetMapping(value = "getFact")
	public ResponseEntity<CatFactDTO> getFact(){
		
		return getCatFactService.execute(100);
		
	}

}
