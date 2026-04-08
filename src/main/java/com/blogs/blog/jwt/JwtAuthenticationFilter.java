package com.blogs.blog.jwt;

import java.util.List;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogs.blog.config.CustomUserDetailsService;
import com.blogs.blog.entities.user.repo.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null;

//		header: Authorization       Bearer [jwt]
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

//		so it is passed the first step
		if(token != null && JwtUtil.isTokenValid(token)) {

			UserDetailsService userDetailsService = new CustomUserDetailsService(userRepository);

			UserDetails userDetails = userDetailsService.loadUserByUsername(JwtUtil.getClaims(token).getSubject());

			List<String> strRoles = JwtUtil.getClaims(token).get("roles", List.class);
			List<SimpleGrantedAuthority> roles = strRoles.stream().map(SimpleGrantedAuthority::new).toList();
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					roles
					);

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);

	}

}
