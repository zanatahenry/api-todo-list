package com.zanatahenry.TodoList.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDTO {
  @NotEmpty(message = "Título é obrigatório!")
  private String titulo;

  private String descricao;

  private String status;
}
