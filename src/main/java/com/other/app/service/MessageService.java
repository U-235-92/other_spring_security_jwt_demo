package com.other.app.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.repository.MessageRepository;
import com.other.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
	
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	private final UserService userService;

	public void postMessage(MessageDTO messageDTO, Principal principal) {
		Message message = new Message();
		message.setText(messageDTO.getText());
		messageRepository.save(message);
	}
	
	public void deleteMessage(long id) {
		messageRepository.deleteById(id);
	}
	
	public void deleteUserMessages(String username) {
		userService.deleteUserMessages(username);
	}
	
	public void editMessage(long id, MessageDTO messageDTO) {
		Message message = getMessage(id);
		message.setText(messageDTO.getText());
		messageRepository.save(message);
	}
	
	public Message getMessage(long id) {
		return messageRepository.getReferenceById(id);
	}
	
	public List<Message> getUserMessages(String username) {
		return userRepository.findUserMessages(username);
	}
	
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}
}
