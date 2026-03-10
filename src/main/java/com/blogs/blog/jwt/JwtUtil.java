package com.blogs.blog.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	
//	Generating a web token for the user
	public static String generateToken(User user) {
		
		return Jwts.builder()
				.subject(user.getUsername())
//				the expiration date for the token. We will check it's
//				expiration by ourselves
				.expiration(new Date(System.currentTimeMillis() + 300_000))
				.signWith(getSignKey())
				.compact();
		
	}
	
//	claims are contents of token, the data that it holds in it.
	public static Claims getClaims(String token) {
		
		return Jwts.parser()
				.verifyWith(getSignKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}
	
	public static Boolean isTokenValid(String token) {
		return !isExpired(token);
	}
	
	private static Boolean isExpired(String token) {
		return getClaims(token)
				.getExpiration()
				.before(new Date());
	}

	private static SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode("yourSecretKeyAndItMustBeLongEnoughToBeMoreSecure");

		return Keys.hmacShaKeyFor(keyBytes);
	}

}
