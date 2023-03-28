package com.practice.todolist.service;

import com.practice.todolist.dto.TodoDto;

import java.util.List;
import java.util.Map;

public interface ITodoService {
    public List<TodoDto> selectAll();
    public TodoDto selectOne(Long tno);
    public String insertTodo(String title, String writer, String date);
    public String updateTodo(Long tno, String title, String dueDate, boolean finished);
    public String deleteTodo(Long tno);
    public int totalCountTodo();

    //검색
    public List<TodoDto> findTodoList(Map<String, String> map);   //검색 기능
}
