package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.DTOs.UserDTO;
import com.zanatahenry.TodoList.entities.UserEntity;
import com.zanatahenry.TodoList.models.ResponseHandler;
import com.zanatahenry.TodoList.services.UserService;
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
public class RegisterController {
  private final UserService service;

  @PostMapping
  public ResponseEntity<Object> register (@RequestBody @Valid UserDTO dto) {
    UserEntity createdUser = service.createUser(dto);

    return ResponseHandler.generateResponse("Usuário criado com sucesso!", HttpStatus.CREATED, createdUser.getId());
  }
}
