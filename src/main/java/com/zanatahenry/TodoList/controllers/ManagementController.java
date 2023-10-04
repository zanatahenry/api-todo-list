package com.zanatahenry.TodoList.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ManagementController {
  @GetMapping("/alou")
  public String alou () {
    return  "alou";
  }
}
