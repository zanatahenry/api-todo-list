package com.zanatahenry.TodoList.controllers.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
  public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object response) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (response != null) map.put("data", response);
    map.put("message", message);
    map.put("status", status.value());

    return new ResponseEntity<Object>(map, status);
  }
}
