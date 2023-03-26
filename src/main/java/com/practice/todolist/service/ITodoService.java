package com.practice.todolist.service;

import com.practice.todolist.dto.TodoDto;

import java.util.List;

public interface ITodoService {
    public List<TodoDto> selectAll();
    public TodoDto selectOne(int tno);
    public String insertTodo(String title, String writer, String date);
    public String updateTodo(int tno, String title, String dueDate, boolean finished);
    public String deleteTodo(int tno);
    public int totalCountTodo();
}
