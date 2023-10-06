package com.zanatahenry.TodoList.services;

import com.zanatahenry.TodoList.entities.TodoList;
import com.zanatahenry.TodoList.enums.TodoStatus;
import com.zanatahenry.TodoList.repositories.TodoListRepository;
import com.zanatahenry.TodoList.services.impl.TodoListServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {
  @InjectMocks
  TodoListServiceImpl todoListService;

  @Mock
  TodoListRepository todoListRepository;

  TodoList todoList;

  @BeforeEach
  public void setUp () {
    this.todoList = new TodoList(
      1,
      "Teste",
      "Teste",
      TodoStatus.TODO,
      LocalDate.now()
      );
  }

  @Test
  void getAllTodo () {
    when(todoListRepository.findAll()).thenReturn(Collections.singletonList(todoList));
    List<TodoList> todoLists = todoListService.getAll();

    Assertions.assertEquals(Collections.singletonList(todoList), todoLists);

    // Verifica se o repository é chamado 1 vez
    verify(todoListRepository).findAll();

    // Verifica se o repository não é chamado mais de 1 vez
    verifyNoMoreInteractions(todoListRepository);
  }

  @Test
  void getTodoById () {
    when(todoListRepository.findById(todoList.getId())).thenReturn(Optional.ofNullable(todoList));
    Optional<TodoList> todoLists = todoListService.getById(todoList.getId());

    Assertions.assertEquals(Optional.ofNullable(todoList), todoLists);
    verify(todoListRepository).findAll();
    verifyNoMoreInteractions(todoListRepository);
  }
}
