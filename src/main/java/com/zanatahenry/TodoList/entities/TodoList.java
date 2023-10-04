package com.zanatahenry.TodoList.entities;

import com.zanatahenry.TodoList.enums.TodoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "todos")
public class TodoList {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(length = 100)
  @NotEmpty(message = "Titulo é obrigatório!")
  private String titulo;

  @Column(length = 1000)
  private String descricao;

  @Column(length = 50)
  @Enumerated(EnumType.STRING)
  private TodoStatus status;

  @Column(name = "created_at")
  private LocalDate createdAt;
}
