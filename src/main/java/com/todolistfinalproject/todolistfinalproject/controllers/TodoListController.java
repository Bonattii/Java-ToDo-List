package com.todolistfinalproject.todolistfinalproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  // GET http://localhost:8080/tasks/update/{id}
  // Shows the page with the form to update a task
  // IMPORTANT: th:href="@{/tasks/update/{id}(id=${todolist.id})}"
  @GetMapping("/update/{id}")
  public String displayUpdateTaskForm(@PathVariable("id") int id, Model model) {
    // Try to find the task information by Id
    Optional<TodoList> todoListOld = todoListRepository.findById(id);

    // If not able to find the task
    if (!todoListOld.isPresent()) {
      throw new IllegalArgumentException("Invalid Task.");
    }

    // Get the content of the old task
    TodoList todoList = todoListOld.get();
    model.addAttribute("todolist", todoList);

    return "tasks/update-task";
  }

  // POST http://localhost:8080/tasks/update/save
  // IMPORTANT: th:href="@{/tasks/update/save}"
  @PostMapping("/update/save")
  public String updateExistingTask(TodoList todolist) {
    todoListRepository.save(todolist);

    return "redirect:/tasks";
  }

  // GET http://localhost:8080/tasks/delete/{id}
  // IMPORTANT : th:href="@{/tasks/delete/{id}(id=${todolist.id})}"
  // OTHER IMPORTANT: th:data-confirm-delete="|You want to delete this task?|"
  // onclick="if (!confirm(this.getAttribute('data-confirm-delete'))) return
  // false"
  @GetMapping("/delete/{id}")
  public String showDeleteTask(@PathVariable("id") int id, Model model) {
    // Try to find the task by Id and create a instance of TodoList
    // If didn't find jus thow an argument exception
    TodoList todolist = todoListRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Task"));

    todoListRepository.delete(todolist);

    return "redirect:/tasks";

  }
}
