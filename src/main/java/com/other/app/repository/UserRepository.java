package com.other.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String usernme);
}
