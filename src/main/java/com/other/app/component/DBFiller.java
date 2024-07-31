package com.other.app.component;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.other.app.entity.Role;
import com.other.app.entity.Status;
import com.other.app.entity.User;
import com.other.app.repository.DbJpaUserRepository;

import io.jsonwebtoken.lang.Collections;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DBFiller {

	private final DbJpaUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@PostConstruct
	protected void addUsersIntoDatabase() {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("1"));
		admin.setEmail("admin@my.aq");
		admin.setRole(Role.ADMIN);
		admin.setStatus(Status.ACTIVE);
		User simple = new User();
		simple.setUsername("simple");
		simple.setPassword(passwordEncoder.encode("2"));
		simple.setEmail("simple@my.aq");
		simple.setRole(Role.SIMPLE_USER);
		simple.setStatus(Status.ACTIVE);
		User extended = new User();
		extended.setUsername("extended");
		extended.setPassword(passwordEncoder.encode("3"));
		extended.setEmail("extended@my.aq");
		extended.setRole(Role.EXTENDED_USER);
		extended.setStatus(Status.ACTIVE);
		userRepository.saveAll(Collections.of(admin, simple, extended));
	}
}
