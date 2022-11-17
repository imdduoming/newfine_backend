package com.example.nf.newfine_backend.test.dto;

import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseTestResultsDto {

    private String subject;

    private String questionNum;

    private String correctAns;

    private String points;

    private String type;

    private double correctAnsRate;

    private int choose1;

    private int choose2;

    private int choose3;

    private int choose4;

    private int choose5;

    private double proportion1;

    private double proportion2;

    private double proportion3;

    private double proportion4;

    private double proportion5;

    public CourseTestResults toCourseTestResults() {
        return CourseTestResults.builder()
                .subject(subject)
                .questionNum(questionNum)
                .correctAns(correctAns)
                .points(points)
                .type(type)
                .correctAnsRate(correctAnsRate)
                .choose1(choose1)
                .choose2(choose2)
                .choose3(choose3)
                .choose4(choose4)
                .choose5(choose5)
                .proportion1(proportion1)
                .proportion2(proportion2)
                .proportion3(proportion3)
                .proportion4(proportion4)
                .proportion5(proportion5)
                .build();
    }
}
