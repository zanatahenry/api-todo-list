package com.zanatahenry.TodoList.services;

import com.zanatahenry.TodoList.DTOs.SigninDTO;
import com.zanatahenry.TodoList.DTOs.UserDTO;
import com.zanatahenry.TodoList.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
  UserEntity createUser (UserDTO dto);

  UserDetails authenticate(SigninDTO dto);
}
