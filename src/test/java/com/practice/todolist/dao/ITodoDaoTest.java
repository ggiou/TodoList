package com.practice.todolist.dao;

import com.practice.todolist.dto.TodoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest     //스프링부트 어플리케이션 테스트에 필요한 거의 모든 의존성을 제공
class ITodoDaoTest {
//추후에 시스템이 커질 경우 system.out이 아닌 assertThat 등
// 확인작업을 통해야 효율 개선.. -> 고칠 예정, 우선 간단한 확인 작업
// + assertThat을 통한 빠른 테스트 코드 -> service에서 적용    
    @Autowired
    ITodoDao dao;
    
    @Test
    void selectAll() {
        List<TodoDto> todoDtos = dao.selectAll();

        for (TodoDto todoDto : todoDtos) {
            System.out.println(todoDto.getTno());
        }
    }   // select all test

    @Test
    void selectOne() {
        long n = 1;
        TodoDto todoDto = dao.selectOne(n);
        System.out.println(todoDto.getTno());
    } // select one test

    @Test
    void insertTodo() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("YYYY/MM/dd");
        //Dto에서 LocalDate 타입이니 변환해 넣기..

        dao.insertTodo("test1", "user1" ,date.format(fm));
        List<TodoDto> todoDtos = dao.selectAll();
        for (TodoDto todoDto : todoDtos) {
            System.out.println(todoDto.getTitle());
        }
    } // inset test

    @Test
    void updateTodo() {
        dao.updateTodo(2L, "test2","2021/11/10",true);
        List<TodoDto> todoDtos = dao.selectAll();
        for (TodoDto todoDto : todoDtos) {
            System.out.println(todoDto.getTitle());
        }
    }   // update test

    @Test
    void deleteTodo() {
        dao.deleteTodo(3L);
        List<TodoDto> todoDtos = dao.selectAll();
        for (TodoDto todoDto : todoDtos) {
            System.out.println(todoDto.getTitle());
        }
    }   // delete test
}
//전부 정상 동작 확인 = dao -> mysql + mybatis를 통한 db 조작 성공..

/* test 작성시 좋은 단축키
*  dao 기능 부르고 그 앞에 crtl + art + v -> 타입에 맞게 자동으로 변수 생성
*  for( : ) 자동 생성 -> iter 하고 엔터
* */