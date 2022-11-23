package com.todolistfinalproject.todolistfinalproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TODOLIST_TABLE")
public class TodoList {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String taskName;
  private String taskDescription;

  public TodoList() {

  }

  public TodoList(String taskName, String taskDescription) {
    this.taskName = taskName;
    this.taskDescription = taskDescription;
  }

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
