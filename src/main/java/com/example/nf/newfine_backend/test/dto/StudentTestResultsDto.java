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
    private String studentCode;
    private int totalScore;
    private int score1;
    private int score2;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;
    private String q7;
    private String q8;
    private String q9;
    private String q10;
    private String q11;
    private String q12;
    private String q13;
    private String q14;
    private String q15;
    private String q16;
    private String q17;
    private String q18;
    private String q19;
    private String q20;
    private String q21;
    private String q22;
    private String q23;
    private String q24;
    private String q25;
    private String q26;
    private String q27;
    private String q28;
    private String q29;
    private String q30;
    private String q31;
    private String q32;
    private String q33;
    private String q34;
    private String q35;
    private String q36;
    private String q37;
    private String q38;
    private String q39;
    private String q40;

    public StudentTestResults toStudentTestResults() {
        return StudentTestResults.builder()
                .name(name)
                .studentCode(studentCode)
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
