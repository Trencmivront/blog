package com.blogs.blog.catfact;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blogs.blog.impl.Query;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetCatFactService implements Query<Integer, CatFactDTO>{
	
	private final RestTemplate restTemplate;

	@Override
	public ResponseEntity<CatFactDTO> execute(Integer i) {
		
		CatFact catFact = restTemplate
				.getForObject(URI.create("https://catfact.ninja/fact"), CatFact.class);
		
		CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
		
		return ResponseEntity.ok(catFactDTO);
	}
	


}
