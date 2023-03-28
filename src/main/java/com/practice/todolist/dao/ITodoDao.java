package com.practice.todolist.dao;

import com.practice.todolist.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITodoDao {
    public List<TodoDto> selectAll();
    public TodoDto selectOne(Long tno);
    public void insertTodo(String title, String writer, String date);
    public void updateTodo(Long tno, String title, String date, boolean finished);
    public void deleteTodo(Long tno);
    public int totalCountTodo();

    //검색
    public List<TodoDto> findTodoList(Map<String, String> map);   //검색 기능


}
//db에 접근해 데이터베이스 조작을 위한 interface 설정