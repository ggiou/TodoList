package com.practice.todolist.service;

import com.practice.todolist.dto.TodoDto;

import java.util.List;

public interface ITodoService {
    public List<TodoDto> selectAll();
    public TodoDto selectOne(long tno);
    public String insertTodo(String title, String dueDate, String writer);
    public String updateTodo(long tno, String title, String dueDate, boolean finished);
    public String deleteTodo(long tno);
}
