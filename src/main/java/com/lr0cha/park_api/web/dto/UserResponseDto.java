package com.lr0cha.park_api.web.dto;

import com.lr0cha.park_api.entities.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {
	private Long id;
	private String username;
	private Role role;
}
