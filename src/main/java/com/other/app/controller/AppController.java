package com.other.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.service.AppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class AppController {

	private final AppService appService;
	
	@PostMapping("/post_msg")
	@ResponseBody
	public String postMessage(MessageDTO messageDTO) {
		appService.postMessage(messageDTO);
		String response = "Message posted success";
		return response;
	}
	
	@DeleteMapping("/delete_msg/{id}")
	@ResponseBody
	public String deleteMessage(long id) {
		appService.deleteMessage(id);
		String response = String.format("Message with id %d deleted success", id);
		return response;
	}
	
	@DeleteMapping("/delete_user_msgs/{username}")
	@ResponseBody
	public String deleteAllUserMessages(String username) {
		appService.deleteAllUserMessages(username);
		String response = String.format("All messages of user %s deleted success", username);
		return response;
	}
	
	@GetMapping("/get_all_msgs")
	@ResponseBody
	public List<Message> getAllMessages() {
		return appService.getAllMessages();
	}
	
	@GetMapping("/get_user_msgs/{username}")
	@ResponseBody
	public List<Message> getUserMessages(@PathVariable String username) {
		return appService.getUserMessages(username);
	}
}
