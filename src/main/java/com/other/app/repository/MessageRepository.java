package com.other.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM messages m WHERE m.user_id = (SELECT u.id FROM users u WHERE u.username = :username)")
	List<Message> findUserMessagesByUsername(String username);
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM messages m WHERE m.user_id = (SELECT u.id FROM users u WHERE u.username = :username)")
	void deleteUserMessages(String username);
}
