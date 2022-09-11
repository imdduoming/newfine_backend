package com.example.nf.newfine_backend.test.dto.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeacherKillerDto {

    private Double rate; // 오답률 ?
    private String q_num; // 문제번호
    private String right_ans; //문제 답
    private String  most_chosen;

}
