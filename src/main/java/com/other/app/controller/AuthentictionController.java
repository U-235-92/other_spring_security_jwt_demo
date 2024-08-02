package com.other.app.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.AuthenticateRequestDTO;
import com.other.app.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class AuthentictionController {

	private final AuthenticationService authenticationService;
	
	@PostMapping("/authenticate")
	@ResponseBody
	@Transactional
	public String authenticate(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
		return authenticationService.authenticate(authenticateRequestDTO);
	}
}
