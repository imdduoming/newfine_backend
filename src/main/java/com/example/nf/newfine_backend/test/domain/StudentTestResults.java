package com.example.nf.newfine_backend.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentTestResults {
    @Id
    @Column(name = "student_test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String name;

    @Column
    private String testCode;

    @Column
    private int totalScore;

    @Column
    private int score1;

    @Column
    private int score2;

    @Column
    private int q1;

    @Column
    private int q2;

    @Column
    private int q3;

    @Column
    private int q4;

    @Column
    private int q5;

    @Column
    private int q6;

    @Column
    private int q7;

    @Column
    private int q8;

    @Column
    private int q9;

    @Column
    private int q10;

    @Column
    private int q11;

    @Column
    private int q12;

    @Column
    private int q13;

    @Column
    private int q14;

    @Column
    private int q15;

    @Column
    private int q16;

    @Column
    private int q17;

    @Column
    private int q18;

    @Column
    private int q19;

    @Column
    private int q20;

    @Column
    private int q21;

    @Column
    private int q22;

    @Column
    private int q23;

    @Column
    private int q24;

    @Column
    private int q25;
    @Column
    private int q26;
    @Column
    private int q27;
    @Column
    private int q28;

    @Column
    private int q29;

    @Column
    private int q30;
    @Column
    private int q31;
    @Column
    private int q32;
    @Column
    private int q33;
    @Column
    private int q34;
    @Column
    private int q35;
    @Column
    private int q36;
    @Column
    private int q37;
    @Column
    private int q38;
    @Column
    private int q39;
    @Column
    private int q40;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Test test;

    @Builder
    public StudentTestResults(Test test, String name, String testCode, int totalScore, int score1, int score2, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9, int q10, int q11, int q12, int q13, int q14, int q15, int q16, int q17, int q18, int q19, int q20, int q21, int q22, int q23, int q24, int q25, int q26, int q27, int q28, int q29, int q30, int q31, int q32, int q33, int q34, int q35, int q36, int q37, int q38, int q39, int q40) {
        this.test = test;
        this.name = name;
        this.testCode = testCode;
        this.totalScore = totalScore;
        this.score1 = score1;
        this.score2 = score2;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
        this.q9 = q9;
        this.q10 = q10;
        this.q11 = q11;
        this.q12 = q12;
        this.q13 = q13;
        this.q14 = q14;
        this.q15 = q15;
        this.q16 = q16;
        this.q17 = q17;
        this.q18 = q18;
        this.q19 = q19;
        this.q20 = q20;
        this.q21 = q21;
        this.q22 = q22;
        this.q23 = q23;
        this.q24 = q24;
        this.q25 = q25;
        this.q26 = q26;
        this.q27 = q27;
        this.q28 = q28;
        this.q29 = q29;
        this.q30 = q30;
        this.q31 = q31;
        this.q32 = q32;
        this.q33 = q33;
        this.q34 = q34;
        this.q35 = q35;
        this.q36 = q36;
        this.q37 = q37;
        this.q38 = q38;
        this.q39 = q39;
        this.q40 = q40;
    }
}
