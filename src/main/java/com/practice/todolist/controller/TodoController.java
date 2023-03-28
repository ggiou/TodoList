package com.practice.todolist.controller;

import com.practice.todolist.dao.ITodoDao;
import com.practice.todolist.dto.TodoDto;
import com.practice.todolist.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TodoController {
    @Autowired
    ITodoService todoService;

    @GetMapping("")
    public String todoRoot() throws Exception{
        return "redirect:/todo/list"; //첫 화면 list 보이기
    }

    @GetMapping("list")
    public String todoListPage(Model model){
        model.addAttribute("todoList", todoService.selectAll());
        return "/todo/list"; //todoList 전체 보여주기 - 메인페이지
    }

    @GetMapping("read")
    public String todoRead(HttpServletRequest request, Model model){
        Long sTno = Long.parseLong(request.getParameter("tno"));
        model.addAttribute("todo", todoService.selectOne(sTno));
        return "/todo/read"; //선택한 todo 게시글 보여주기
    }

    @GetMapping("registerForm")
    public String registerForm(){
        return "/register"; //글 작성을 위한 폼으로 이동
    }

    @PostMapping("register")
    public String todoRegiter(TodoDto dto){
        todoService.insertTodo(dto.getTitle(), dto.getWriter(), dto.getDueDate().toString());

        return "redirect:/todo/list"; //글 업로드 후 도로 main 페이지로 이동
    }

    @PostMapping("modify")
    public String todoModify (TodoDto dto){
        todoService.updateTodo(dto.getTno(), dto.getTitle(), dto.getDueDate().toString(), dto.isFinished());

        return "redirect:/todo/read"; //수정 후에는 read 페이지로 이동
    }

    @GetMapping("delete")
    public String todoDelete (HttpServletRequest request){
        todoService.deleteTodo(Long.parseLong(request.getParameter("tno")));

        return "redirect:/todo/list";
    }

    @PostMapping("search")
    public String todoSearch (HttpServletRequest request, Model model){
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        String finished = request.getParameter("finished");

        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("writer", writer);
        map.put("date1", date1);
        map.put("date2", date2);
        map.put("finished", finished);

        List<TodoDto> todoList = todoService.findTodoList(map);

        return "redirect:/todo/list";
    }

}
