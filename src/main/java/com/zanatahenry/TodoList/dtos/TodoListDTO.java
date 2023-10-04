package com.zanatahenry.TodoList.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDTO {
  private String titulo;
  private String descricao;
  private String status;
}
