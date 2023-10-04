package com.zanatahenry.TodoList.service;

import com.zanatahenry.TodoList.dtos.TodoListDTO;
import com.zanatahenry.TodoList.entity.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListService {
  TodoList save (TodoListDTO dto);

  List<TodoList> getAll();

  Optional<TodoList> getById(Integer id);

  Optional<TodoList> updateTodo(Integer id, TodoListDTO dto);

  void delete(Integer id);
}
