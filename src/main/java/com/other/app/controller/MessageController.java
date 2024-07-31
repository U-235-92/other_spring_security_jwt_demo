package com.other.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

	private final MessageService messageService;
	
	@ResponseBody
	@PostMapping("/post_message")
	public String postMessage(@RequestBody MessageDTO messageDTO, Principal principal) {
		messageService.postMessage(messageDTO, principal);
		return "[Mesage posted]";
	}
	
	@ResponseBody
	@DeleteMapping("/delete_message/{id}")
	public String deleteMessage(@PathVariable long id) {
		messageService.deleteMessage(id);
		String response = String.format("[Message with id: %d deleted]", id);
		return response;
	}
	
	@ResponseBody
	@DeleteMapping("/delete_user_messages/{username}")
	public String deleteUserMessages(@PathVariable String username) {
		messageService.deleteUserMessages(username);
		String response = String.format("[All the messages of user %s deleted]", username);
		return response;
	}
	
	@ResponseBody
	@PutMapping("/edit_message/{id}")
	public String editMessage(@PathVariable long id, MessageDTO messageDTO) {
		messageService.editMessage(id, messageDTO);
		String response = String.format("[Message with id: %d edited]", id);
		return response;
	}
	
	@ResponseBody
	@GetMapping("/get_message/{id}")
	public Message getMessage(long id) {
		Message message = messageService.getMessage(id);
		return message;
	}
	
	@ResponseBody
	@GetMapping("/get_user_messages/{username}")
	public List<Message> getUserMessages(@PathVariable String username) {
		List<Message> messages = messageService.getUserMessages(username);
		return messages;
	}
	
	@ResponseBody
	@GetMapping("/get_all_messages")
	public List<Message> getAllMessages() {
		List<Message> messages = messageService.getAllMessages();
		return messages;
	}
}
