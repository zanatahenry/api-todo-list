package com.zanatahenry.TodoList.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "todo")
public class TodoList {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String titulo;

  private String descricao;

  private String status;

  @Column(name = "createdAt", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
  private LocalDate createdAt;

}
