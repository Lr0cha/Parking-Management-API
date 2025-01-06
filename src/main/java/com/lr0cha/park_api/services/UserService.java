package com.lr0cha.park_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lr0cha.park_api.entities.User;
import com.lr0cha.park_api.exceptions.EntityNotFoundException;
import com.lr0cha.park_api.exceptions.UsernameUniqueViolationException;
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
		return repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuário com id %s não encontrado",id)));
	}
	
	@Transactional
	public User insert(User user) {
		try {
			return repository.save(user);
		}catch(DataIntegrityViolationException ex) {
			throw new UsernameUniqueViolationException(String.format("Username %s já cadastrado", user.getUsername()));
		}
		
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
