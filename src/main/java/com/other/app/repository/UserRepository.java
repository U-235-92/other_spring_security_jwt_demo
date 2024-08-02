package com.other.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.entity.Message;
import com.other.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String usernme);
//	@Query(value = "Select Message m From User u Where u.username = username")
	@Query(nativeQuery = true, value = "Select * From messages Inner Join users On messages.user_id = users.id Where users.username = :username")
	List<Message> findUserMessages(String username);
}
