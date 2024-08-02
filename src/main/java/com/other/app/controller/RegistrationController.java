package com.other.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.RegistrationRequestDTO;
import com.other.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class RegistrationController {

	private final UserService userService;
	
	@PostMapping("/registration")
	@ResponseBody
	public String registrate(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
		userService.registrateUser(registrationRequestDTO);
		String response = String.format("User %s registrated success", registrationRequestDTO.getUsername());
		return response;
	}
}
