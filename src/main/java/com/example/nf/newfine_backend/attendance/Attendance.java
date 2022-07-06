package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.Course;
import com.example.nf.newfine_backend.Student;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Attendance extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "attendance_id")
    private Long ssId;

    @Column(nullable = false)
    private String student_name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private Course course;

//    @Builder
//    public Attendance(Student student) {
//        this.student=student;
//
//
//    }
    @Builder
    public Attendance(AttendanceDto attendanceDto) {
        this.student_name=attendanceDto.getStudentName();


    }



}
