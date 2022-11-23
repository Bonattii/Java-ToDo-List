package com.todolistfinalproject.todolistfinalproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  @PostMapping("/update/{id}")
  public String updateExistingTask() {

    return "";
  }

  // POST http://localhost:8080/tasks/delete
  @DeleteMapping("/delete/{id}")
  public String deleteExistingTask(@PathVariable int id) {
    Optional<TodoList> todolist = todoListRepository.findById(id);

    if (todolist.isPresent()) {
      todoListRepository.delete(todolist.get());

      return "redirect:/tasks";
    } else {
      throw new RuntimeException("Task not found for the given id.");
    }
  }
}
