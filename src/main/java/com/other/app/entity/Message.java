package com.other.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@Data
public class Message {

	@Id
	@GeneratedValue
	@Column(name = "message_id")
	private long id;
	@Column(name = "message_text")
	private String text;
}
