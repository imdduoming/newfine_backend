package com.example.nf.newfine_backend.course;


import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.branch.domain.Branch;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.test.domain.Test;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "course")
public class Course {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "course_id")
    private Long id;

    // 강의 이름
    @Column(nullable = false)
    private String cName;

    // 중학교 고등학교
    @Column(nullable = false)
    private String school;

    @Column
    private String start_time;

    @Column
    private String end_time;

    @Column
    private String days;

    // 수학 , 과학탐구
    @Column(nullable = false)
    private String subject;

    // ex . 기백 , 미적
    @Column(nullable = false)
    private String subjectType;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="course", cascade = { CascadeType.REMOVE})
    private List<Attendance> attendances;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="course", cascade = { CascadeType.REMOVE})
    private List<Listener> listeners;


//
//    @JsonIgnore
//    @JsonBackReference //순환참조 방지
//    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST})
//    private List<THomework> thomeworks;


//    @OneToMany(mappedBy="course", cascade = {CascadeType.REMOVE})
//    private List<THomework> tHomeworks;
//
    @JsonManagedReference
    @ManyToOne
    private Teacher teacher;

    @JsonBackReference  // 순환참조 방지
    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST})
    private List<Test> tests;

    @JsonManagedReference
    @ManyToOne
    private Branch branch;

    @Builder
    public Course(String name, String school, String subject, String subjectType, String start_time , String end_time) {
      this.cName = name;
      this.school = school;
      this.subject = subject;
      this.subjectType = subjectType;
      this.start_time = start_time;
      this.end_time = end_time;
    }





}
