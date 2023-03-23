package com.practice.todolist.service;

import com.practice.todolist.dto.TodoDto;

import java.util.List;

public interface ITodoService {
    public List<TodoDto> selectAll();
    public TodoDto selectOne(long tno);
    public void insertTodo(String title, String date, String writer);
    public void updateTodo(long tno, String title, String date, boolean finished);
    public void deleteTodo(long tno);
}
