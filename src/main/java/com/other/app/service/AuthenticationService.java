package com.other.app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.other.app.dto.AuthenticateRequestDTO;
import com.other.app.entity.User;
import com.other.app.repository.UserRepository;
import com.other.app.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	
	public String authenticate(AuthenticateRequestDTO authenticateRequestDTO) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequestDTO.getUsername(), authenticateRequestDTO.getPassword()));
		User user = userRepository.findByUsername(authenticateRequestDTO.getUsername());
		return jwtUtil.generateToken(user);
	}

}
