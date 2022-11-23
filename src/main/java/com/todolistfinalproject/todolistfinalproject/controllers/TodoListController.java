package com.todolistfinalproject.todolistfinalproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolistfinalproject.todolistfinalproject.dao.iTodoListRepository;
import com.todolistfinalproject.todolistfinalproject.entities.TodoList;

// it says that this class will be a controller
@Controller
@RequestMapping("/tasks")
public class TodoListController {
  // Get the implementations from the repository
  @Autowired
  iTodoListRepository todoListRepository;

  // GET http://localhost:8080/tasks
  @GetMapping("")
  public String displayHomePageWithTasks(Model model) {
    // Create a list with all the info from the database
    List<TodoList> todolists = todoListRepository.findAll();

    // Add the attribute "todo" to the model to be used on the HTML
    model.addAttribute("todolist", todolists);

    return "home/index";
  }

  // POST http://localhost:8080/tasks/new
  @PostMapping("/new")
  public String createNewTask(TodoList todoList, Model model) {
    todoListRepository.save(todoList);

    return "redirect:/tasks";
  }

  // POST http://localhost:8080/tasks/update
  @PostMapping("/update")
  public String updateExistingTask() {
    return "";
  }

  // POST http://localhost:8080/tasks/delete
  @PostMapping("/delete")
  public String deleteExistingTask() {
    return "";
  }
}
