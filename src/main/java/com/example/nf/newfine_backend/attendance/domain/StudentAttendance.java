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
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StudentAttendance {

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

    @Column
    private boolean attend = false;
    @Column
    private boolean islate = false;
    @Column
    @Temporal(TemporalType.TIMESTAMP) // 날짜와 시간, 데이터베이스 timestamp 타입과 매핑 (2020-12-18 23:36:33)
    private Date time;

    @Builder
    public StudentAttendance(Student student,Attendance attendance,Date time,Boolean attend,Boolean islate) {
        this.student=student;
        this.attendance=attendance;
        this.time=time;
        this.attend=attend; // 출석  여부
        this.islate=islate; // 지각 여부


    }




}
