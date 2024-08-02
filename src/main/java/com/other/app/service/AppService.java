package com.other.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.entity.User;
import com.other.app.repository.MessageRepository;
import com.other.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppService {

	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	
	public void postMessage(MessageDTO messageDTO) {
		Message message = new Message();
		message.setText(messageDTO.getText());
		messageRepository.save(message);
	}

	public void deleteMessage(long id) {
		messageRepository.deleteById(id);
	}

	public void deleteAllUserMessages(String username) {
		User user = userRepository.findByUsername(username);
		user.getMessages().clear();
		userRepository.save(user);
	}

	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	public List<Message> getUserMessages(String username) {
		List<Message> messages = userRepository.findUserMessages(username);
		return messages;
	}

}
