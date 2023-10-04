package com.zanatahenry.TodoList.services.impl;

import com.zanatahenry.TodoList.DTOs.TodoListDTO;
import com.zanatahenry.TodoList.entities.TodoList;
import com.zanatahenry.TodoList.enums.TodoStatus;
import com.zanatahenry.TodoList.repositories.TodoListRepository;
import com.zanatahenry.TodoList.services.TodoListService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {
  private final TodoListRepository repository;

  @Override
  public TodoList save(TodoListDTO dto) {
    TodoList todo = new TodoList();
    todo.setDescricao(dto.getDescricao());
    todo.setTitulo(dto.getTitulo());
    todo.setStatus(TodoStatus.TODO);
    todo.setCreatedAt(LocalDate.now());

    repository.save(todo);
    return todo;
  }

  @Override
  public List<TodoList> getAll() {
    return repository.findAll();
  }

  @Override
  public Optional<TodoList> getById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Optional<TodoList> updateTodo(Integer id, TodoListDTO dto) {
    return repository.findById(id)
        .map(todo -> {
          if (StringUtils.isNotBlank(dto.getStatus())) {
            TodoStatus status = TodoStatus.valueOf(dto.getStatus());
            todo.setStatus(status);
          }

          if (StringUtils.isNotBlank(dto.getTitulo())) todo.setTitulo(dto.getTitulo());
          if (StringUtils.isNotBlank(dto.getDescricao())) todo.setDescricao(dto.getDescricao());
          return repository.save(todo);
        });
  }

  @Override
  public void delete(Integer id) {
    Optional<TodoList> todo = repository.findById(id);
    if (todo.isPresent()) {
      repository.delete(todo.get());
    }
  }

  @Override
  public boolean exists(Integer id) {
    return repository.existsById(id);
  }
}
