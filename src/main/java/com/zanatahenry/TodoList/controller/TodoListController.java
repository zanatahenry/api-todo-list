package com.zanatahenry.TodoList.controller;

import com.zanatahenry.TodoList.dtos.TodoListDTO;
import com.zanatahenry.TodoList.entity.TodoList;
import com.zanatahenry.TodoList.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoListController {
  private final TodoListService service;

  @GetMapping
  public List<TodoList> getAllTodo() {
    return service.getAll();
  }

  @GetMapping("{id}")
  public Optional<TodoList> getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PatchMapping("{id}")
  public Optional<TodoList> update (@PathVariable Integer id, @RequestBody TodoListDTO dto) {
    return service.updateTodo(id, dto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Integer save (@RequestBody TodoListDTO dto) {
    TodoList todoListSaved = service.save(dto);
    return todoListSaved.getId();
  }


  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete (@PathVariable Integer id) {
    service.delete(id);
  }
}
