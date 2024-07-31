package com.other.app.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.UserDTO;
import com.other.app.entity.Status;
import com.other.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@ResponseBody
	@PutMapping("/change_user_status/{username}")
	public String changeUserStatus(@PathVariable String username, @RequestBody Status status) {
		userService.changeUserStatus(username, status);
		String response = String.format("The status of the user %s changed on: %s", username, status.name());
		return response;
	}
	
	@ResponseBody
	@PostMapping("/create_user")
	public String createUser(@RequestBody UserDTO userDTO) {
		userService.createUser(userDTO);
		String response = String.format("The user %s created", userDTO);
		return response;
	}
	
	@ResponseBody
	@DeleteMapping("/delete_user/{username}")
	public String deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		String response = String.format("The user %s deleted", username);
		return response;
	}
}
