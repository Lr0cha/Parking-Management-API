package com.lr0cha.park_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lr0cha.park_api.entities.User;
import com.lr0cha.park_api.repositories.UserRepository;



@Service //reconhece como um bean gerenciado
public class UserService {
	
	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public List<User> findAll(){
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return repository.findById(id).get();
	}
	
	@Transactional
	public User insert(User user) {
		return repository.save(user);
	}
	
	@Transactional
	public void updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
		if(!newPassword.equals(confirmPassword)) {
			throw new RuntimeException("Novas senhas não conferem!");
		}
		User user = findById(id);
		if(!user.getPassword().equals(currentPassword)) {
			throw new RuntimeException("Senha atual não confere!");
		}
		user.setPassword(newPassword);
	}

}
