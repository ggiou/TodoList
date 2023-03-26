package com.practice.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private int tno;
    private String title;
    private String writer;
    private LocalDate dueDate;
    private boolean finished;
}
