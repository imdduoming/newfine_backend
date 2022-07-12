package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.Course;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Attendance extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "attendance_id")
    private Long attendanceId;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private Course course;

//    @Builder
//    public Attendance(Student student) {
//        this.student=student;
//
//
//    }
    @Builder
    public Attendance(Course course) {
        this.course=course;

    }



}
