package com.example.nf.newfine_backend.attendance.domain;

import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST})
    @JsonIgnore
    @JsonManagedReference
    private Course course;

    @OneToMany(mappedBy="attendance", cascade = { CascadeType.PERSIST})
    private List<StudentAttendance> studentAttendances;
//
//    @Column
//    @Temporal(TemporalType.TIMESTAMP) // 날짜와 시간, 데이터베이스 timestamp 타입과 매핑 (2020-12-18 23:36:33)
//    private Date startTime;
//
//    @Column
//    @Temporal(TemporalType.TIMESTAMP) // 날짜와 시간, 데이터베이스 timestamp 타입과 매핑 (2020-12-18 23:36:33)
//    private Date endTime;


//    @Builder
//    public Attendance(Student student) {
//        this.student=student;
//
//
//    }

    public Attendance(Course course) {
        this.course=course;

    }





}
