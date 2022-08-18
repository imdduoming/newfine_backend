package com.example.nf.newfine_backend.test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
public class KillerDto {
    private boolean iscorrect;
    private Double rate; // 오답률 ?
    private String student_ans ; // 나의 답
    private String q_num; // 문제번호
    private String right_ans; //문제 답
    private ArrayList<String> most_chosen;

}
