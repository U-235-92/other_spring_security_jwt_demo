package com.other.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.entity.Message;
import com.other.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	@Query(value = "Select Message m From User u Where u.username = username")
	List<Message> findUserMessages(String username);
}
