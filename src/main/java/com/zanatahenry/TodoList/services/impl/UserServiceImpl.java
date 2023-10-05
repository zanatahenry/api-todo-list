package com.zanatahenry.TodoList.services.impl;

import com.zanatahenry.TodoList.DTOs.SigninDTO;
import com.zanatahenry.TodoList.DTOs.UserDTO;
import com.zanatahenry.TodoList.entities.UserEntity;
import com.zanatahenry.TodoList.enums.UserRoles;
import com.zanatahenry.TodoList.exception.ErrorException;
import com.zanatahenry.TodoList.repositories.UserRepository;
import com.zanatahenry.TodoList.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  private final UserRepository repository;

  @Override
  public UserEntity createUser(UserDTO dto) {
    UserEntity newUser = new UserEntity();
    newUser.setCreatedAt(LocalDate.now());
    newUser.setName(dto.getName());
    newUser.setLevel(UserRoles.valueOf(dto.getLevel()));
    newUser.setEmail(dto.getEmail());
    newUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

    repository.save(newUser);
    return newUser;
  }

  @Override
  public UserDetails authenticate(SigninDTO dto) {
    UserDetails userDetails = this.loadUserByEmail(dto.getEmail());
    boolean validPassword = bCryptPasswordEncoder.matches(dto.getPassword(), userDetails.getPassword());
    if (!validPassword) throw new ErrorException("Senha/email inválidos!", HttpStatus.BAD_REQUEST);
    return userDetails;
  }

  public UserDetails loadUserByEmail (String email) throws ErrorException {
    UserEntity user = repository.findByEmail(email)
        .orElseThrow(() -> new ErrorException("Usuário não encontrado!", HttpStatus.NOT_FOUND));

    return User
        .builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .roles(String.valueOf(user.getLevel()))
        .build();
  }
}
