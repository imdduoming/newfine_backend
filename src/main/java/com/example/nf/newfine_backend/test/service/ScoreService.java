package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.example.nf.newfine_backend.test.domain.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreService {


    public Double get_avg(int total_num, List<StudentTestResults> allStudents){
        int total_score=0;
        Double avg ;
        for (StudentTestResults studentTestResults1 : allStudents){
            total_score+=studentTestResults1.getTotalScore();
        }
        avg = Double.valueOf(total_score) / Double.valueOf(total_num);
        double new_avg = (double)Math.round(avg*100)/100;

        return new_avg;

    }




}
