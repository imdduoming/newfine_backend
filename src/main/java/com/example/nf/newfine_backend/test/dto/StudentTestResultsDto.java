package com.example.nf.newfine_backend.test.dto;

import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentTestResultsDto {
    private String name;
    private String testCode;
    private int totalScore;
    private int score1;
    private int score2;
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int q5;
    private int q6;
    private int q7;
    private int q8;
    private int q9;
    private int q10;
    private int q11;
    private int q12;
    private int q13;
    private int q14;
    private int q15;
    private int q16;
    private int q17;
    private int q18;
    private int q19;
    private int q20;
    private int q21;
    private int q22;
    private int q23;
    private int q24;
    private int q25;
    private int q26;
    private int q27;
    private int q28;
    private int q29;
    private int q30;
    private int q31;
    private int q32;
    private int q33;
    private int q34;
    private int q35;
    private int q36;
    private int q37;
    private int q38;
    private int q39;
    private int q40;

    public StudentTestResults toStudentTestResults() {
        return StudentTestResults.builder()
                .name(name)
                .testCode(testCode)
                .totalScore(totalScore)
                .score1(score1)
                .score2(score2)
                .q1(q1)
                .q2(q2)
                .q3(q3)
                .q4(q4)
                .q5(q5)
                .q6(q6)
                .q7(q7)
                .q8(q8)
                .q9(q9)
                .q10(q10)
                .q11(q11)
                .q12(q12)
                .q13(q13)
                .q14(q14)
                .q15(q15)
                .q16(q16)
                .q17(q17)
                .q18(q18)
                .q19(q19)
                .q20(q20)
                .q21(q21)
                .q22(q22)
                .q23(q23)
                .q24(q24)
                .q25(q25)
                .q26(q26)
                .q27(q27)
                .q28(q28)
                .q29(q29)
                .q30(q30)
                .q31(q31)
                .q32(q32)
                .q33(q33)
                .q34(q34)
                .q35(q35)
                .q36(q36)
                .q37(q37)
                .q38(q38)
                .q39(q39)
                .q40(q40)
                .build();
    }
}
