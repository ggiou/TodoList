package com.practice.todolist.service;

import com.practice.todolist.dao.ITodoDao;
import com.practice.todolist.dto.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService{

    @Autowired
    private ITodoDao dao;

    @Override
    public List<TodoDto> selectAll() {
        if (dao.selectAll().size() == 0){
            System.out.println("현재 게시물이 하나도 존재하지 않습니다.");
        }
            return dao.selectAll();
    }

    @Override
    public TodoDto selectOne(int tno) {
        if (dao.selectOne(tno) == null){
            System.out.println(tno+"번 게시물이 존재하지 않습니다.");
        }
        return dao.selectOne(tno);
    }

    @Override
    public String insertTodo(String title, String writer, String date) {
        dao.insertTodo(title, writer, date);

        return title + "이 정상적으로 생성 되었습니다.";  //message 전달
    }

    @Override
    public String updateTodo(int tno, String title, String dueDate, boolean finished) {
        dao.updateTodo(tno, title, dueDate, finished);

        return title + "이 정상적으로 업데이트 되었습니다.";
    }

    @Override
    public String deleteTodo(int tno) {
        dao.deleteTodo(tno);

        return tno + "번 todo가 삭제 되었습니다.";
    }

    @Override
    public int totalCountTodo() {
        return dao.totalCountTodo();
    }
}
