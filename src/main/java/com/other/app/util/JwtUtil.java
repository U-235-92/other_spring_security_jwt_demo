package com.other.app.util;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.other.app.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${app.jwt.secret}")
	private String secret;// = "dgs9d8g9080ds80h9sd80h98df90h890f890";
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
}
