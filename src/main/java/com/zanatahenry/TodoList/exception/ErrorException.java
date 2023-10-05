package com.zanatahenry.TodoList.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorException extends RuntimeException {
  HttpStatus status;
  public ErrorException (String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
