package com.example.nf.newfine_backend.Homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SHomeworkCheckDto {
    private Long id;
    private Long shId;
    private String name;
    private String title;
    private char grade;
    private boolean ischecked;



}
