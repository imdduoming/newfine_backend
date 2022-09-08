package com.example.nf.newfine_backend.test.dto.student;

import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.example.nf.newfine_backend.test.dto.NotCorrectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TestResultDto {
    // 순위 , 평균
    private int rank;
    private Double avg;
    // 오답률 best 5
    private List<NotCorrectDto> notCorrectDtos;
    private int myScore;
    private int total;


}

