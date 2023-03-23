package com.practice.todolist.service;

import com.practice.todolist.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService{
    @Override
    public List<TodoDto> selectAll() {
        return null;
    }

    @Override
    public TodoDto selectOne(long tno) {
        return null;
    }

    @Override
    public void insertTodo(String title, String date, String writer) {

    }

    @Override
    public void updateTodo(long tno, String title, String date, boolean finished) {

    }

    @Override
    public void deleteTodo(long tno) {

    }
}
