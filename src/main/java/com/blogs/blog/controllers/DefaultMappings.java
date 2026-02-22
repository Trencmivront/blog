package com.blogs.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultMappings {
	
	
	@GetMapping("/error")
	public String pageNotFound(){
		
		return "page_not_found";
		
	}

}
