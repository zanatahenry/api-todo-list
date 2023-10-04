package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.controllers.response.ResponseHandler;
import com.zanatahenry.TodoList.DTOs.TodoListDTO;
import com.zanatahenry.TodoList.entities.TodoList;
import com.zanatahenry.TodoList.services.TodoListService;
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
public class TodoListController {
  private final TodoListService service;

  @GetMapping
  public ResponseEntity<Object> getAllTodo() {
    try {
      List<TodoList> allTodo = service.getAll();
      return ResponseHandler.generateResponse("Todo's encontrados com sucesso!", HttpStatus.OK, allTodo);
    } catch (Exception e) {
      return ResponseHandler.generateResponse("Falha ao encontrar todo's!", HttpStatus.BAD_REQUEST, e);
    }
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> getById(@PathVariable Integer id) {
    try {
     Optional<TodoList> findedTodo = service.getById(id);
     if (findedTodo.isEmpty()) {
       return ResponseHandler.generateResponse("Falha ao encontrar todo!", HttpStatus.BAD_REQUEST, null);
     }

      return ResponseHandler.generateResponse("Todo encontrado com sucesso!", HttpStatus.OK, findedTodo);
    } catch (Exception e) {
      return ResponseHandler.generateResponse("Falha ao encontrar todo!", HttpStatus.BAD_REQUEST, e);
    }
  }

  @PatchMapping("{id}")
  public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody TodoListDTO dto) {
    try {
      boolean existsId = service.exists(id);
      if (!existsId) {
        return ResponseHandler.generateResponse(
            "Todo não encontrado!",
            HttpStatus.NOT_FOUND,
            null);
      }

     Optional<TodoList> updatedTodo = service.updateTodo(id, dto);

      return ResponseHandler.generateResponse("Todo atualizado com sucesso!", HttpStatus.OK, updatedTodo);
    } catch (Exception e) {
      return ResponseHandler.generateResponse("Erro ao atualizar todo!", HttpStatus.BAD_REQUEST, e);
    }
  }

  @PostMapping
  public ResponseEntity<Object> save (@RequestBody @Valid TodoListDTO dto) {
    try {
      TodoList todoListSaved = service.save(dto);

      return ResponseHandler.generateResponse("Todo criado com sucesso!", HttpStatus.CREATED, todoListSaved.getId());
    } catch (Exception e) {
      return ResponseHandler.generateResponse("Erro ao criar todo!", HttpStatus.BAD_REQUEST, null);
    }
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> delete (@PathVariable Integer id) {
    try {
      boolean existsId = service.exists(id);
      if (!existsId) {
        return ResponseHandler.generateResponse(
          "Todo não encontrado!",
          HttpStatus.NOT_FOUND,
          null);
      }

      service.delete(id);
      return ResponseHandler.generateResponse(
        "Todo deletado com sucesso!",
        HttpStatus.OK,
        null);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(
        "Erro ao deletar todo!",
        HttpStatus.BAD_REQUEST,
        null);
    }
  }
}
