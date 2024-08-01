package com.other.app.service;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.other.app.dto.UserDTO;
import com.other.app.entity.Status;
import com.other.app.entity.User;
import com.other.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getPermitions()
				.stream()
				.map(permition -> new SimpleGrantedAuthority(permition.name()))
				.collect(Collectors.toList()));
	}

	public User getUserFromPrincipal(Principal principal) {
		String username = principal.getName();
		return userRepository.findByUsername(username);
	}
	
	public void changeUserStatus(String username, Status status) {
		User user = userRepository.findByUsername(username);
		user.setStatus(status);
		userRepository.save(user);
	}
	
	public void deleteUserMessages(String username) {
		User user = userRepository.findByUsername(username);
		user.getMessages().clear();
		userRepository.save(user);
	}

	public void createUser(UserDTO userDTO) {
		User user = User.fromUserDTO(userDTO);
		userRepository.save(user);
	}

	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username);
		userRepository.delete(user);
	}
}
