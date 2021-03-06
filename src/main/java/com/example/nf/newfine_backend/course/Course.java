package com.example.nf.newfine_backend.course;


import com.example.nf.newfine_backend.teacher.Teacher;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Course {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "course_id")
    private Long cId;

    // 강의 이름
    @Column(nullable = false)
    private String cName;

    // 중학교 고등학교
    @Column(nullable = false)
    private String school;

    // 수학 , 과학탐구
    @Column(nullable = false)
    private String subject;

    // ex . 기백 , 미적
    @Column(nullable = false)
    private String subjectType;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST})
    private List<Attendance> attendances;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST})
    private List<Listener> listeners;


//    @OneToMany(mappedBy="course", cascade = {CascadeType.REMOVE})
//    private List<THomework> tHomeworks;
//
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;



}
