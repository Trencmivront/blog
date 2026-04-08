package com.blogs.blog.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlPageController {

	@GetMapping("/")
	public String mainPage() {
		return "index";
	}

	@GetMapping("/logIn")
	public String logInPage() {
		return "login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@GetMapping("/profile")
	public String profilePage() {
		return "profile";
	}
}