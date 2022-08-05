package com.example.nf.newfine_backend.test.domain;

import com.example.nf.newfine_backend.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseTestResults {
    @Id
    @Column(name = "course_test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String subject;

    @Column
    private String questionNum;

    @Column
    private String correctAns;

    @Column
    private String points;

    @Column
    private String type;

    @Column
    private double correctAnsRate;

    @Column
    private int choose1;

    @Column
    private int choose2;

    @Column
    private int choose3;

    @Column
    private int choose4;

    @Column
    private int choose5;

    @Column
    private double proportion1;

    @Column
    private double proportion2;

    @Column
    private double proportion3;

    @Column
    private double proportion4;

    @Column
    private double proportion5;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Test test;

    @Builder
    public CourseTestResults(Test test, String subject, String questionNum, String correctAns, String points, String type, double correctAnsRate, int choose1, int choose2, int choose3, int choose4, int choose5, double proportion1, double proportion2, double proportion3, double proportion4, double proportion5) {
        this.test=test;
        this.subject = subject;
        this.questionNum = questionNum;
        this.correctAns = correctAns;
        this.points = points;
        this.type = type;
        this.correctAnsRate = correctAnsRate;
        this.choose1 = choose1;
        this.choose2 = choose2;
        this.choose3 = choose3;
        this.choose4 = choose4;
        this.choose5 = choose5;
        this.proportion1=proportion1;
        this.proportion2=proportion2;
        this.proportion3=proportion3;
        this.proportion4=proportion4;
        this.proportion5=proportion5;
    }
}
