package com.other.app.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Role {

	ADMIN(Set.of(Permition.DELETE_MESSAGE, Permition.EDIT_MESSAGE, Permition.POST_MESSAGE, Permition.READ_MESSAGE)),
	SIMPLE_USER(Set.of(Permition.POST_MESSAGE, Permition.READ_MESSAGE)), 
	EXTENDED_USER(Set.of(Permition.EDIT_MESSAGE, Permition.POST_MESSAGE, Permition.READ_MESSAGE));
	
	private Set<Permition> permitions = new HashSet<>();
}
