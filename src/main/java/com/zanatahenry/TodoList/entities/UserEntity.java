package com.zanatahenry.TodoList.entities;

import com.zanatahenry.TodoList.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;
  private String password;
  private String name;

  @Enumerated(EnumType.STRING)
  private UserRoles level;

  private LocalDate createdAt;
}
