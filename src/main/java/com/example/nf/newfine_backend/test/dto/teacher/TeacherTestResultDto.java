package com.example.nf.newfine_backend.test.dto;

import com.example.nf.newfine_backend.test.dto.teacher.TeacherNotCorrectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TeacherTestResultDto {
    //  평균
    private Double avg;
    // 오답률 best 5
    private List<TeacherNotCorrectDto> notCorrectDtos;
    private int highest;
    private int lowest;
}
