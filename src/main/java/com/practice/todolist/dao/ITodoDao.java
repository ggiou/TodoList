package com.practice.todolist.dao;

import com.practice.todolist.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITodoDao {
    public List<TodoDto> selectAll();
    public TodoDto selectOne(long tno);
    public void insertTodo(String title, String writer, String date);
    public void updateTodo(long tno, String title, String date, boolean finished);
    public void deleteTodo(long tno);

}
//db에 접근해 데이터베이스 조작을 위한 interface 설정