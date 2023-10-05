package com.zanatahenry.TodoList.controllers;

import com.zanatahenry.TodoList.models.ResponseHandler;
import com.zanatahenry.TodoList.exception.ErrorException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

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

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException (IllegalArgumentException e) {
    String message = "Ops, algo deu errado!";
    String errors = e.getMessage();

    return ResponseHandler.generateResponse(message, HttpStatus.INTERNAL_SERVER_ERROR, errors);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<Object> handleExpiredJwtException (ExpiredJwtException e) {
    String message = "Usuário não autenticado!";
    String errors = e.getMessage();

    return ResponseHandler.generateResponse(message, HttpStatus.UNAUTHORIZED, errors);
  }
}
