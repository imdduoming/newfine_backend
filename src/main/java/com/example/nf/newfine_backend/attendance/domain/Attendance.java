package com.example.nf.newfine_backend.attendance.domain;

import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Course course;

    @OneToMany(mappedBy="attendance", cascade = { CascadeType.PERSIST})
    @JsonBackReference //순환참조 방지
    private List<StudentAttendance> studentAttendances;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime startTime;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime endTime;

    public Attendance(Course course,LocalDateTime startTime,LocalDateTime endTime) {
        this.course=course;
        this.startTime=startTime;
        this.endTime=endTime;

    }





}
