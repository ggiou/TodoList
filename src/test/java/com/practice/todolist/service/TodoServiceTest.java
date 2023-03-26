package com.practice.todolist.service;

import com.practice.todolist.dto.TodoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class TodoServiceTest {
//asserThat을 통한 빠른 테스트..

    @Autowired
    TodoService service;
    //autowired로 injection해 자동 bean 생성해 사용가능..

    @DisplayName("todo 리스트 전체 불러오기 테스트")
    @Test
    void selectAll() {
        List<TodoDto> todoDtos = service.selectAll();
        int count = service.totalCountTodo();

        assertThat(todoDtos.size()).isEqualTo(count);
        // 전체 게시글 수와 받아온 todoDtos(게시글 리스트)가 같다면 테스트 성공
    }

    @DisplayName("선택한 todo 게시글 불러오기 테스트")
    @Test
    void selectOne() {
        List<TodoDto> todoDtos = service.selectAll();
        TodoDto firstDto = todoDtos.get(0);
        TodoDto todoDto = service.selectOne(firstDto.getTno());
        //전체 리스트의 첫번째 게시글을 가져와 그 게시글의 tno을 통해 selectOne으로 가져온 후

        assertThat(todoDto).isEqualTo(firstDto);
        //만약 두 게시글의 같다면 제대로 tno번째 게시글을 가져오는 것이니 테스트 성공
    }

    @DisplayName("todo 게시글 올리기 테스트")
    @Test
    void insertTodo() {
        LocalDate date =  LocalDate.parse("2021-03-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        service.insertTodo("테스트입니당", "사용자1", date.toString());

        selectOne();
        //selectAll을 하게 된다면, tno를 desc(내림차순)로 찾도록 설정해두어서
        //업로드한 게시물이 가장 최근으로 index를 0을 가지게 된다.
        //가장 최신거가 제대로 찾아졌는지 확인하는 방식으로 Test코드 selectOne을 작성했으므로 그대로 사용하였다.
    }

    @DisplayName("todo 리스트 업데이트 테스트")
    @Test
    void updateTodo() {
        int tno = service.selectAll().get(0).getTno();  //가장 최신 게시물의 tno 가져오기
        service.updateTodo(tno, "수정테스트입니다.", "2022-01-11" ,true);
        TodoDto todoDto = service.selectOne(tno);
        assertThat(todoDto.getTitle()).isEqualTo("수정테스트입니다.");
        assertThat(todoDto.getDueDate()).isEqualTo("2022-01-11");
        assertThat(todoDto.isFinished()).isEqualTo(true);
        // 업데이트한 내용을 찾아와 일치하는지 확인, 입력한 데이터와 검색한 데이터가 같다면 테스트 성공
    }

    @Test
    void deleteTodo() {
        int tno = service.selectAll().get(0).getTno(); //가장 최신 게시물의 tno 가져오기
        service.deleteTodo(tno);
        if(service.selectOne(tno) == null) tno = -1;
        assertThat(tno).isEqualTo(-1);
        //만약 selectOne으로 삭제한 게시글을 찾아왔을때 존재하지 않는다면 tno에 -1을
        //넣어 aseertThat을 통해 -1이 나오면 정상적으로 삭제되었고, 테스트 성공
        //추후에 exception 처리 후 null포인터 에러가 나오면 정상적으로 되도록 변경 예정
    }

    @Test
    void findTodoList(){
        Map<String, String> map = new HashMap<>();
        map.put("title", null);
        map.put("writer", null);
        map.put("date1", null);
        map.put("date2", null);
        map.put("finished", null);
        //null, 즉 아무런 체크를 하지 않는다면 서비스 내부에서 해당조건에 대해서는 검색을 하지 않는다 생각

        List<TodoDto> todoList = service.findTodoList(map);

        assertThat(todoList.size()).isEqualTo(0);
        //검색조건이 없으면 = null, finished의 경우 무저건 true, false의 값으로 반환되야 하므로
        //테스트를 위해 넣은 코드로 인해 finished에 null이 들어가면 2가 반환되므로 아무것도 검색되지 않는다
        //조건에 해당하는 결과가 없으므로 사이즈가 0이라면 알맞게 작동했다 생각해 테스트 성공

        /* for (TodoDto todoDto : todoList) {
            System.out.println(todoDto.getTno());
            System.out.println(todoDto.getTitle());
            System.out.println(todoDto.getWriter());
            System.out.println(todoDto.getDueDate());
            System.out.println(todoDto.isFinished());
            System.out.println("-------------------");
        }*/
        //검색한 todoList 출력
    }
}