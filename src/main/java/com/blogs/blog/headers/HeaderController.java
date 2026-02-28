package com.blogs.blog.headers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.blog.entities.user.User;

@RestController
public class HeaderController {

//	we can use headers to validate type of file we accept
//	or to authenticate the user
	@GetMapping("/header")
	public ResponseEntity<String> getHeader(@RequestHeader(required = false, defaultValue = "TR") String val){
		
		if(val.equals("TR"))
			return ResponseEntity.ok("YAŞASIN IRKIMIZ!");
		else
			return ResponseEntity.ok("diğer ırk");
		
	}
	
//	produces values are things we will accept
	@GetMapping(value = "/header/user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<User> getUser(){
		
		User user = User.builder()
				.email("ahdsfk@akjhjsdfka")
				.id(1l)
				.name("ahksd")
				.password("jadsopgfasjdfoa")
				.surname("gıasbflıs")
				.username("ıpquhe9*0fh4w")
				.build();
		
		return ResponseEntity.ok(user);
		
	}
	
}
