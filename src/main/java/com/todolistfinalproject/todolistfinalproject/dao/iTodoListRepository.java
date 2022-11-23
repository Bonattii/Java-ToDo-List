package com.todolistfinalproject.todolistfinalproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolistfinalproject.todolistfinalproject.entities.TodoList;

public interface iTodoListRepository extends JpaRepository<TodoList, Integer> {
  // Overrides the findAll method from the JpaRepository, to says that the type
  // of the list is the type of TodoList entity
  @Override
  public List<TodoList> findAll();
}
