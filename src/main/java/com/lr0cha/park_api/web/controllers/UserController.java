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

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity <List<User>> findAll(){
		List<User> user = service.findAll();
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		user = service.insert(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user){
		user = service.updatePassword(id, user.getPassword());
		return ResponseEntity.ok().body(user);
	}	
}
