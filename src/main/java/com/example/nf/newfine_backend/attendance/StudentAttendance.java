package com.example.nf.newfine_backend.attendance;


import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StudentAttendance extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "studentattendance_id")
    private Long ssId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private Attendance attendance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private Student student;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Course course;

    //    @Builder
//    public Attendance(Student student) {
//        this.student=student;
//
//
//    }
    @Builder
    public StudentAttendance(Student student,Attendance attendance) {
        this.student=student;
        this.attendance=attendance;


    }



}
