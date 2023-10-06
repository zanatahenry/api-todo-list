package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.DTOs.SigninDTO;
import com.zanatahenry.TodoList.DTOs.SigninResponseDTO;
import com.zanatahenry.TodoList.entities.UserEntity;
import com.zanatahenry.TodoList.models.ResponseHandler;
import com.zanatahenry.TodoList.security.jwt.JwtService;
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
@RequestMapping("/signin")
@Tag(name = "Signin", description = "Signin API")
public class SigninController {
  private final UserService userService;
  private final JwtService jwtService;
  @PostMapping
  public ResponseEntity<Object> signin (@RequestBody @Valid SigninDTO dto) {
    userService.authenticate(dto);
    SigninResponseDTO responseDTO = new SigninResponseDTO();
    UserEntity user = UserEntity
        .builder()
        .email(dto.getEmail())
        .password(dto.getPassword())
        .build();

    String token = jwtService.getToken(user);
    responseDTO.setUser(user);
    responseDTO.setToken(token);
    return ResponseHandler.generateResponse("Usuário encontrado com sucesso!", HttpStatus.OK, responseDTO);
  }
}
