package com.zanatahenry.TodoList.DTOs;

import com.zanatahenry.TodoList.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponseDTO {
  private UserEntity user;
  private String token;
}
