package com.other.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
