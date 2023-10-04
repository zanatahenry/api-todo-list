package com.zanatahenry.TodoList.controllers.exceptions;

public class ErrorException extends RuntimeException {
  public ErrorException (String message) {
    super(message);
  }
}
