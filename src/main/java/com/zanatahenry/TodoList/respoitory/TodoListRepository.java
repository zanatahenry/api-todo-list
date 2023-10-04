package com.zanatahenry.TodoList.respoitory;

import com.zanatahenry.TodoList.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
