package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.course.Course;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

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
    @JsonManagedReference
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
