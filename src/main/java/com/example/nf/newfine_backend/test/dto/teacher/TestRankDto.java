package com.example.nf.newfine_backend.test.dto.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TestRankDto {
    private int rank; // 오답률 순위
    private String name ; // 이름
    private int score; // 점수
}