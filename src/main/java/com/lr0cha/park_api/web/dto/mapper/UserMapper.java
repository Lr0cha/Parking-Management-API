package com.lr0cha.park_api.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.lr0cha.park_api.entities.User;
import com.lr0cha.park_api.web.dto.UserCreateDto;
import com.lr0cha.park_api.web.dto.UserResponseDto;

public class UserMapper {
	
	public static User toUser(UserCreateDto createDto) {
		return new ModelMapper().map(createDto, User.class);
	}
	
	public static UserResponseDto toDto(User user) {
		return new ModelMapper().map(user, UserResponseDto.class);
	}
	
	public static List<UserResponseDto> toListDto(List<User> users){
		return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
	}

}
