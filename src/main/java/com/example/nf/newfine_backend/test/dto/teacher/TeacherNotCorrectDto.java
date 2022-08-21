package com.example.nf.newfine_backend.test.dto.teacher;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeacherNotCorrectDto {
    private String right_ans ; // 문제의 답
    private String q_num; // 문제번호
    private Double rate; // 오답률
    private int q_rank; // 오답률 순위

}
