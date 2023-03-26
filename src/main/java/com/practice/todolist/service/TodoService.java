package com.practice.todolist.service;

import com.practice.todolist.dao.ITodoDao;
import com.practice.todolist.dto.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

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


    @Override
    public List<TodoDto> findTodoList(Map<String, String> map) {

        if(map.get("title")==null) map.replace("title", "%");
        if(map.get("writer")==null) map.replace("writer", "%");
        //만약 들어오는 제목, 작성자 조건이 없다면 전체라 생각해.. %로 변환
        if(map.get("date1")==null) map.replace("date1","00/00/00");
        if(map.get("date2")==null) map.replace("date2", "9999/12/30");
        //만약 들어오는 날짜 조건이 없다면 전체기간이라 생각해 변환

        //검색시 완료여부에 체크가 되어있으면 1, 없으면 0으므로 완료여부의 조건
        //즉 한가지의 조건은 무저건 포함된 상태로 검색이되므로, 결과값을 반환할 수 있다..

        if(map.get("finished")==null) map.replace("finished", "2");
        //finished의 무저건 값(0 or 1)이 들어가지만,
        //테스트를 위해 null일 경우 2를 넣어 아무것도 검색될 수 없도록 임의 설정..

        return dao.findTodoList(map);
    }   //검색기능
}
