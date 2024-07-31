package com.other.app.dto;

import com.other.app.entity.Role;
import com.other.app.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

	private String username;
	private String password;
	private String email;
	private Role role;
	private Status status;
}
