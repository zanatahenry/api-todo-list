package com.zanatahenry.TodoList.DTOs;

import com.zanatahenry.TodoList.enums.UserRoles;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @NotEmpty(message = "Email é obrigatório!")
  private String email;

  @NotEmpty(message = "Senha é obrigatória!")
  private String password;

  @NotEmpty(message = "Nome é obrigatório!")
  private String name;

  @NotEmpty(message = "Level é obrigatório!")
  private String level;
}
