package com.zanatahenry.TodoList.repositories;

import com.zanatahenry.TodoList.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
