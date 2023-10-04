package com.zanatahenry.TodoList.services;

import com.zanatahenry.TodoList.DTOs.TodoListDTO;
import com.zanatahenry.TodoList.entities.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListService {
  TodoList save (TodoListDTO dto);

  List<TodoList> getAll();

  Optional<TodoList> getById(Integer id);

  Optional<TodoList> updateTodo(Integer id, TodoListDTO dto);

  void delete(Integer id);

  boolean exists(Integer id);

}
