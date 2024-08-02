package com.other.app.util;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.other.app.configuration.JWTConfigurationProperties;
import com.other.app.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

	private JWTConfigurationProperties jwtConfigurationProperties;
	@Value("${app.duration}")
	private Duration d;
	
	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getPermitions());
		Date issued = new Date();
		Date expired = new Date(issued.getTime() + d.toMillis());
		return Jwts.builder()
			.claims(claims)
			.issuedAt(issued)
			.expiration(expired)
			.subject(user.getUsername())
			.signWith(Keys.hmacShaKeyFor(jwtConfigurationProperties.getSecret().getBytes()))
			.compact();
	}
}
