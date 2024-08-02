package com.other.app.util;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.other.app.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${app.jwt.secret}")
	private String secret;
	@Value("${app.jwt.accessTokenLifetime}")
	private Duration accessTokenLifetime;
	
	public String generateToken(User user) {
		Date issuedDate = new Date();
		Date expiredDate = new Date(issuedDate.getTime() + accessTokenLifetime.toMillis());
		Map<String, Object> claims = new HashMap<>();
		claims.put("permitions", user.getPermitions());
		return Jwts
				.builder()
				.issuedAt(issuedDate)
				.expiration(expiredDate)
				.subject(user.getUsername())
				.claims(claims)
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.compact();
	}
	
	public String getUsername(String jwt) {
		return getTokenClaims(jwt).getSubject();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> permitions(String jwt) {
		return getTokenClaims(jwt).get("permitions", List.class);
	}
	
	private Claims getTokenClaims(String jwt) {
		JwtParser jwtParser = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build();
		return jwtParser.parseSignedClaims(jwt).getPayload();
	}
}
