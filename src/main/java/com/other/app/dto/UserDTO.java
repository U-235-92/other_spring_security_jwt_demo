package com.other.app.dto;

import java.util.Set;

import com.other.app.entity.Permition;
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
	private Set<Permition> permitions;
	private Status status;
}
