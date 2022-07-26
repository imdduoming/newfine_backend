package com.example.nf.newfine_backend.student.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "point_id")
    private Long id;

    private String contents;

    private int score;

    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.LAZY)  // 포인트(Many) : 학생(One)
    @JoinColumn(name="student_id")
    @JsonManagedReference
    private Student owner;

    static public Point createPoint(Student student, String contents, int score) {
        Point point=new Point();
        point.contents=contents;
        point.datetime=LocalDateTime.now();
        point.score=score;
        point.owner=student;
        return point;
    }

}
