package com.example.nf.newfine_backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyAllTestDto { // 시험 회차별 결과 (순위 , 점수 , 회차)
    private int test_num; // 테스트 회차

    private int rank;

    private int score;

}
