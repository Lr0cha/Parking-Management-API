package com.lr0cha.park_api.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lr0cha.park_api.services.UserService;

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UserController {
	@Autowired
	private UserService service;
}
