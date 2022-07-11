package com.example.nf.newfine_backend.attendance;


import com.example.nf.newfine_backend.BaseTimeEntity;
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

    @Column(nullable = false)
    private String studentPhone;

//
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Student student;
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
    public StudentAttendance(StudentAttendanceDto studentAttendanceDto) {
        this.studentPhone= studentAttendanceDto.getStudentPhoneNumber();


    }



}
