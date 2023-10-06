package com.zanatahenry.TodoList.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@Tag(name = "Management", description = "Management API")
public class ManagementController {
  @GetMapping("/alou")
  public String alou () {
    return  "alou";
  }
}
