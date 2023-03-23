package com.practice.todolist.dao;

import com.practice.todolist.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public class TodoDao implements ITodoDao {
    @Override
    public List<TodoDto> selectAll() {
        return null;
    }

    @Override
    public TodoDto selectOne() {
        return null;
    }

    @Override
    public void insertTodo() {

    }

    @Override
    public void updateTodo() {

    }

    @Override
    public void deleteTodo() {

    }
}
