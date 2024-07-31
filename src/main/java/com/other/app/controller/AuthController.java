package com.other.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.AuthenticationRequesDTO;
import com.other.app.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authServce;
	
	@ResponseBody
	@PostMapping("/authenticate")
	public String authenticteUser(@RequestBody AuthenticationRequesDTO authenticationRequesDTO) {
		authServce.authenticate(authenticationRequesDTO.getUsername(), authenticationRequesDTO.getPassword());
		String response = String.format("User %s logged success", authenticationRequesDTO.getUsername());
		return response;
	}
}
