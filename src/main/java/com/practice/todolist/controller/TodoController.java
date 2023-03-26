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

@Controller
public class TodoController {
    @Autowired
    ITodoService todoService;

    @GetMapping("")
    public String todoRoot() throws Exception{
        return "redirect:list"; //첫 화면 list 보이기
    }

    @GetMapping("list")
    public String todoListPage(Model model){
        model.addAttribute("todoList", todoService.selectAll());
        return "/list"; //todoList 전체 보여주기 - 메인페이지
    }

    @GetMapping("read")
    public String todoRead(HttpServletRequest request, Model model){
        int sTno = Integer.parseInt(request.getParameter("tno"));
        model.addAttribute("todo", todoService.selectOne(sTno));
        return "/read"; //선택한 todo 게시글 보여주기
    }

    @GetMapping("registerForm")
    public String registerForm(){
        return "/register"; //글 작성을 위한 폼으로 이동
    }

    @PostMapping("register")
    public String todoRegiter(TodoDto dto){
        todoService.insertTodo(dto.getTitle(), dto.getWriter(), dto.getDueDate().toString());

        return "redirect:list"; //글 업로드 후 도로 main 페이지로 이동
    }

    @PostMapping("modify")
    public String todoModify (TodoDto dto){
        todoService.updateTodo(dto.getTno(), dto.getTitle(), dto.getDueDate().toString(), dto.isFinished());

        return "redirect:read"; //수정 후에는 read 페이지로 이동
    }

    @GetMapping("delete")
    public String todoDelete (HttpServletRequest request){
        todoService.deleteTodo(Integer.parseInt(request.getParameter("tno")));

        return "redirect:list";
    }

}
