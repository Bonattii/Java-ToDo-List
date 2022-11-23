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
    model.addAttribute("todolists", todolists);

    return "home/index";
  }

  // GET http://localhost:8080/tasks/new
  // Shows the page with the form to add a task
  @GetMapping("/new")
  public String displayCreateTaskForm(Model model) {
    model.addAttribute("todolist", new TodoList());

    return "tasks/new-task";
  }

  // POST http://localhost:8080/tasks/save
  @PostMapping("/save")
  public String createNewTask(TodoList todoList, Model model) {
    todoListRepository.save(todoList);

    return "redirect:/tasks";
  }

  // GET http://localhost:8080/tasks/update
  // Shows the page with the form to update a task
  @GetMapping("/update")
  public String displayUpdateTaskForm() {
    return "tasks/update-task";
  }

  // POST http://localhost:8080/tasks/update/save
  // IMPORTANT: th:href="@{/tasks/update/save/{id}(id=${todolist.id})}"
  @PostMapping("/update/save/{id}")
  public String updateExistingTask(TodoList todoList, Model model) {
    int id = todoList.getId();
    // Create a auxiliar todolist class that will get the id from the db
    TodoList todolistAux = todoListRepository.findById(id).get();

    todolistAux.setTaskName(todoList.getTaskName());
    todolistAux.setTaskDescription(todoList.getTaskDescription());
    todoListRepository.save(todolistAux);

    return "redirect:/tasks";
  }

  // POST http://localhost:8080/tasks/delete
  @DeleteMapping("/delete/{id}")
  public String deleteExistingTask(@PathVariable int id) {
    // Try to get the id from the header
    Optional<TodoList> todolist = todoListRepository.findById(id);

    // If it is possible to get the id, them delete the task with this id
    if (todolist.isPresent()) {
      todoListRepository.delete(todolist.get());

      return "redirect:/tasks";
    } else {
      throw new RuntimeException("Task not found for the given id.");
    }
  }
}
