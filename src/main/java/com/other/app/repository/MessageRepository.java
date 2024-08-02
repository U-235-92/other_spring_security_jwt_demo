package com.other.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query(nativeQuery = true, value = "SELECT m.id AS msg_id, m.text AS msg_text FROM messages m INNER JOIN users u ON (m.user_id = u.id) WHERE u.username = :username")
	List<Message> findUserMessagesByUsername(String username);
}
