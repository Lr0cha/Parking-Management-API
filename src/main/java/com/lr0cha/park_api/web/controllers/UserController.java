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
import com.lr0cha.park_api.web.exceptions.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "Usuários", description="Contém todas as operações relativas ao endpoint do usuário")
@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UserController {
	@Autowired
	private UserService service;
	
	@Operation(summary="Recuperar todos os usuários",description = "Recurso para recuperar todos os usuários já cadastrados",
			responses = {
					@ApiResponse(responseCode = "200", description = "Recurso encontrado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
							@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			})
	@GetMapping
	public ResponseEntity <List<UserResponseDto>> findAll(){
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(UserMapper.toListDto(users));
	}
	@Operation(summary="Recuperar um usuário pelo id",description = "Recurso para recuperar usuário pelo seu id correspondente",
			responses = {
					@ApiResponse(responseCode = "200", description = "Recurso encontrado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
							@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			})
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(UserMapper.toDto(user));
	}
	
	@Operation(summary="Criar um usuário",description = "Recurso para criar um novo usuário",
			responses = {
					@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
					@ApiResponse(responseCode = "409", description = "Email já cadastrado no sistema",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			})
	@PostMapping
	public ResponseEntity<UserResponseDto> insert(@Valid @RequestBody UserCreateDto createDto){
		User user = service.insert(UserMapper.toUser(createDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
	}
	
	@Operation(summary="Redifinir senha do usuário",description = "Recurso para atualizar senha do usuário",
			responses = {
					@ApiResponse(responseCode = "204", description = "Recurso atualizado com sucesso",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
					@ApiResponse(responseCode = "400", description = "Senha(s) não conferem",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			})
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDto dto){
		service.updatePassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
		return ResponseEntity.noContent().build();
	}	
}
