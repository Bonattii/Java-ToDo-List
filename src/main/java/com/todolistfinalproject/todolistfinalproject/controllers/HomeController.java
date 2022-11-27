package com.todolistfinalproject.todolistfinalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  // Just show the home page on that path
  @GetMapping("")
  public String showHomePage() {

    return "home/index";
  }
}
