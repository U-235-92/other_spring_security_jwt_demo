package com.other.app.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.entity.User;
import com.other.app.repository.MessageRepository;
import com.other.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppService {

	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	
	public void postMessage(MessageDTO messageDTO, Principal principal) {
		Message message = new Message();
		message.setText(messageDTO.getText());
		User user = userRepository.findByUsername(principal.getName());
		user.getMessages().add(message);
		userRepository.save(user);
	}

	public void deleteMessage(long id) {
		messageRepository.deleteById(id);
	}

	public void deleteAllUserMessages(String username) {
		messageRepository.deleteUserMessages(username);
	}

	public List<Message> readMessages() {
		return messageRepository.findAll();
	}

	public List<Message> readUserMessages(String username) {
		List<Message> messages = messageRepository.findUserMessagesByUsername(username);
		return messages;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

}
