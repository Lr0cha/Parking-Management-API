package com.lr0cha.park_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lr0cha.park_api.repositories.UserRepository;


@Service //reconhece como um bean gerenciado
public class UserService {
	
	@Autowired
	private UserRepository repository;
}
