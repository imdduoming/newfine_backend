package com.example.nf.newfine_backend.Homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SHomeworkCheckDto {
    private List<String> checkedlist;
    private String state;
}
