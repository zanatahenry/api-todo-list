package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.controllers.response.ResponseHandler;
import com.zanatahenry.TodoList.exception.ErrorException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(ErrorException.class)
  public ResponseEntity<Object> handleErrorException (ErrorException e) {
    String message = e.getMessage();
    HttpStatus status = e.getStatus();

    return ResponseHandler.generateResponse(message, status, null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
    String message = "Campos obrigatórios não informados!";
    List<String> errors = e.getBindingResult().getAllErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();

    return ResponseHandler.generateResponse(message, HttpStatus.BAD_REQUEST, errors);
  }
}
