package com.other.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.entity.User;
import com.other.app.service.AppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class AppController {

	private final AppService appService;
	
	@PostMapping("/message/post")
	@ResponseBody
	public String postMessage(@RequestBody MessageDTO messageDTO, Principal principal) {
		appService.postMessage(messageDTO, principal);
		String response = "Message posted success";
		return response;
	}
	
	@DeleteMapping("/message/delete/id/{id}")
	@ResponseBody
	public String deleteMessage(@PathVariable long id) {
		appService.deleteMessage(id);
		String response = String.format("Message with id %d deleted success", id);
		return response;
	}
	
	@DeleteMapping("/message/delete/username/{username}")
	@ResponseBody
	public String deleteUserMessages(@PathVariable String username) {
		appService.deleteAllUserMessages(username);
		String response = String.format("All messages of user %s deleted success", username);
		return response;
	}
	
	@GetMapping("/message/read/all")
	@ResponseBody
	public List<Message> readMessages() {
		return appService.readMessages();
	}
	
	@GetMapping("/message/read/user/{username}")
	@ResponseBody
	public List<Message> readUserMessages(@PathVariable String username) {
		return appService.readUserMessages(username);
	}
	
	@GetMapping("/user/all")
	@ResponseBody
	public List<User> getUsers() {
		return appService.getUsers();
	}
}
