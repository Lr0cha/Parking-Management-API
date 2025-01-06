package com.lr0cha.park_api.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lr0cha.park_api.entities.User;
import com.lr0cha.park_api.services.UserService;
import com.lr0cha.park_api.web.dto.UserCreateDto;
import com.lr0cha.park_api.web.dto.UserPasswordDto;
import com.lr0cha.park_api.web.dto.UserResponseDto;
import com.lr0cha.park_api.web.dto.mapper.UserMapper;

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity <List<UserResponseDto>> findAll(){
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(UserMapper.toListDto(users));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(UserMapper.toDto(user));
	}
	
	
	@PostMapping
	public ResponseEntity<UserResponseDto> insert(@RequestBody UserCreateDto createDto){
		User user = service.insert(UserMapper.toUser(createDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDto dto){
		service.updatePassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
		return ResponseEntity.noContent().build();
	}	
}
