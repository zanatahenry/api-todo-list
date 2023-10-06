package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.DTOs.TodoListDTO;
import com.zanatahenry.TodoList.entities.TodoList;
import com.zanatahenry.TodoList.exception.ErrorException;
import com.zanatahenry.TodoList.models.ResponseHandler;
import com.zanatahenry.TodoList.services.TodoListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
@Tag(name = "Todo", description = "Todo API")
public class TodoListController {
  private final TodoListService service;

  @GetMapping
  public ResponseEntity<Object> getAllTodo() {
      List<TodoList> allTodo = service.getAll();
      return ResponseHandler.generateResponse("Todo's encontrados com sucesso!", HttpStatus.OK, allTodo);
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> getById(@PathVariable Integer id) {
     Optional<TodoList> findedTodo = service.getById(id);
     if (findedTodo.isEmpty()) throw new ErrorException("Falha ao encontrar todo!", HttpStatus.BAD_REQUEST);

    return ResponseHandler.generateResponse("Todo encontrado com sucesso!", HttpStatus.OK, findedTodo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody TodoListDTO dto) {
    boolean existsId = service.exists(id);
    if (!existsId) throw new ErrorException("Todo não encontrado!", HttpStatus.NOT_FOUND);

   Optional<TodoList> updatedTodo = service.updateTodo(id, dto);

    return ResponseHandler.generateResponse("Todo atualizado com sucesso!", HttpStatus.OK, updatedTodo);
  }

  @PostMapping
  public ResponseEntity<Object> save (@RequestBody @Valid TodoListDTO dto) {
    TodoList todoListSaved = service.save(dto);

    return ResponseHandler.generateResponse("Todo criado com sucesso!", HttpStatus.CREATED, todoListSaved.getId());
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> delete (@PathVariable Integer id) {
    boolean existsId = service.exists(id);
    if (!existsId) throw new ErrorException("Todo não encontrado!", HttpStatus.NOT_FOUND);

    service.delete(id);
    return ResponseHandler.generateResponse("Todo deletado com sucesso!", HttpStatus.OK, null);
  }
}
