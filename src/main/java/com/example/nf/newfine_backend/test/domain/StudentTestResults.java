package com.example.nf.newfine_backend.test.domain;

import com.example.nf.newfine_backend.course.Listener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

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
    private String studentCode;

    @Column
    private int totalScore;

    @Column
    private int score1;

    @Column
    private int score2;

    @Column
    private String q1;

    @Column
    private String q2;

    @Column
    private String q3;

    @Column
    private String q4;

    @Column
    private String q5;

    @Column
    private String q6;

    @Column
    private String q7;

    @Column
    private String q8;

    @Column
    private String q9;

    @Column
    private String q10;

    @Column
    private String q11;

    @Column
    private String q12;

    @Column
    private String q13;

    @Column
    private String q14;

    @Column
    private String q15;

    @Column
    private String q16;

    @Column
    private String q17;

    @Column
    private String q18;

    @Column
    private String q19;

    @Column
    private String q20;

    @Column
    private String q21;

    @Column
    private String q22;

    @Column
    private String q23;

    @Column
    private String q24;

    @Column
    private String q25;
    @Column
    private String q26;
    @Column
    private String q27;
    @Column
    private String q28;

    @Column
    private String q29;

    @Column
    private String q30;
    @Column
    private String q31;
    @Column
    private String q32;
    @Column
    private String q33;
    @Column
    private String q34;
    @Column
    private String q35;
    @Column
    private String q36;
    @Column
    private String q37;
    @Column
    private String q38;
    @Column
    private String q39;
    @Column
    private String q40;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Test test;

    @ManyToOne(fetch = EAGER)
    @JsonManagedReference
    private Listener listener;

    @Builder
    public StudentTestResults(Test test, String name, String studentCode, int totalScore, int score1, int score2, String q1, String q2, String q3, String q4, String q5, String q6, String q7, String q8, String q9, String q10, String q11, String q12, String q13, String q14, String q15, String q16, String q17, String q18, String q19, String q20, String q21, String q22, String q23, String q24, String q25, String q26, String q27, String q28, String q29, String q30, String q31, String q32, String q33, String q34, String q35, String q36, String q37, String q38, String q39, String q40) {
        this.test = test;
        this.name = name;
        this.studentCode = studentCode;
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
