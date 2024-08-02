package com.other.app.service;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.other.app.dto.RegistrationRequestDTO;
import com.other.app.entity.Permition;
import com.other.app.entity.User;
import com.other.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User %s not found", username));
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				user.getPermitions().stream().map(perm -> new SimpleGrantedAuthority(perm.name())).toList());
	}
	
	public void registrateUser(RegistrationRequestDTO registrationRequestDTO) {
		User user = new User();
		user.setUsername(registrationRequestDTO.getUsername());
		user.setPassword(registrationRequestDTO.getPassword());
		user.setEmail(registrationRequestDTO.getEmail());
		user.setPermitions(Set.of(Permition.READ_MESSAGE));
		userRepository.save(user);
	}
}
