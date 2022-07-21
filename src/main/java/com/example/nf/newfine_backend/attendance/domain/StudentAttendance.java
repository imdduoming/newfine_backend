package com.example.nf.newfine_backend.attendance.domain;


import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "sattendance_id")
    private Long sAttendanceId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST})
    @JsonManagedReference
    private Attendance attendance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE})
    @JsonManagedReference
    private Student student;

    @Builder
    public StudentAttendance(Student student,Attendance attendance) {
        this.student=student;
        this.attendance=attendance;


    }



}
