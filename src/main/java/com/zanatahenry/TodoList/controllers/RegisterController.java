package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.DTOs.UserDTO;
import com.zanatahenry.TodoList.entities.UserEntity;
import com.zanatahenry.TodoList.exception.ErrorException;
import com.zanatahenry.TodoList.models.ResponseHandler;
import com.zanatahenry.TodoList.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
@Tag(name = "Register", description = "Register API")
public class RegisterController {
  private final UserService service;

  @PostMapping
  public ResponseEntity<Object> register (@RequestBody @Valid UserDTO dto) {
    boolean emailExists = service.userExists(dto.getEmail());
    if (emailExists) throw new ErrorException("Email já utilizado no sistema", HttpStatus.BAD_REQUEST);

    UserEntity createdUser = service.createUser(dto);

    return ResponseHandler.generateResponse("Usuário criado com sucesso!", HttpStatus.CREATED, createdUser.getId());
  }
}
