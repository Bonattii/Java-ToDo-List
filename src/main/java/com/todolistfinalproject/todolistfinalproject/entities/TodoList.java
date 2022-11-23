package com.todolistfinalproject.todolistfinalproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// It says that this class is an entity and uses a specific table from the db
@Entity
@Table(name = "TODOLIST_TABLE")
public class TodoList {
  // It says that the id will be the id of the entity and will be autogenerated
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String taskName;
  private String taskDescription;

  // Default constructor
  public TodoList() {

  }

  // Parametrixed constructor
  public TodoList(String taskName, String taskDescription) {
    this.taskName = taskName;
    this.taskDescription = taskDescription;
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }
}
