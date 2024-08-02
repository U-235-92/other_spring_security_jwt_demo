package com.other.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private long id;
	@Column(name = "text")
	private String text;
}
