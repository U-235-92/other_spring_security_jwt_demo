package com.other.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.entity.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

	@PostMapping
	@ResponseBody
	public String postMessage() {
		return null;
	}
	
	@ResponseBody
	public String deleteMessage() {
		return null;
	}
	
	@ResponseBody
	public String editMessage() {
		return null;
	}
	
	public Message readMessage(long messageId) {
		return null;
	}
	
	@ResponseBody
	public List<Message> readUserMessages(String username) {
		return null;
	}
	
	@ResponseBody
	public List<Message> readAllMessages() {
		return null;
	}
}
