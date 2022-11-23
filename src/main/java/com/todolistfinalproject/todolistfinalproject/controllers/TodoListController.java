package com.todolistfinalproject.todolistfinalproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.todolistfinalproject.todolistfinalproject.dao.iTodoListRepository;
import com.todolistfinalproject.todolistfinalproject.entities.TodoList;

// it says that this class will be a controller
@Controller
public class TodoListController {
  // Get the implementations from the repository
  @Autowired
  iTodoListRepository todoListRepository;

  // It says that this route will be a of the type GET and the path is
  // http://localhost:8080/
  @GetMapping("/")
  public String displayHomePageWithTasks(Model model) {
    // Create a list with all the info from the database
    List<TodoList> todolists = todoListRepository.findAll();

    // Add the attribute "todo" to the model to be used on the HTML
    model.addAttribute("todolist", todolists);

    return "home/index";
  }
}
