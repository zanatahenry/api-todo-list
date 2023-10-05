package com.zanatahenry.TodoList.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDTO {
  @NotEmpty(message = "Email é obrigatório!")
  private String email;

  @NotEmpty(message = "Senha é obrigatória!")
  private String password;
}
