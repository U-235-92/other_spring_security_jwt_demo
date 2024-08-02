package com.other.app.util;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.other.app.entity.Permition;
import com.other.app.entity.User;
import com.other.app.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DbFiller {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@PostConstruct
	protected void addUsersIntoDatabase() {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("1"));
		admin.setEmail("admin@my.aq");
		admin.setPermitions(Set.of(Permition.values()));
		User simple = new User();
		simple.setUsername("simple");
		simple.setPassword(passwordEncoder.encode("2"));
		simple.setEmail("simple@my.aq");
		simple.setPermitions(Set.of(Permition.READ_MESSAGE, Permition.POST_MESSAGE));
		User extended = new User();
		extended.setUsername("extended");
		extended.setPassword(passwordEncoder.encode("3"));
		extended.setEmail("extended@my.aq");
		extended.setPermitions(Set.of(Permition.READ_MESSAGE, Permition.POST_MESSAGE, Permition.DELETE_MESSAGE));
		userRepository.saveAll(List.of(admin, simple, extended));
	}
}
