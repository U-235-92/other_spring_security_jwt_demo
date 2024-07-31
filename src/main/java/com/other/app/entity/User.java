package com.other.app.entity;

import java.util.ArrayList;
import java.util.List;

import com.other.app.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private long id;
	@Column(name = "user_name", unique = true)
	private String username;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_email")
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private Status status;
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Message> messages = new ArrayList<>();
	
	public static User fromUserDTO(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setRole(userDTO.getRole());
		user.setStatus(userDTO.getStatus());
		return user;
	}
}
