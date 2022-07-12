package com.example.nf.newfine_backend;


import com.example.nf.newfine_backend.attendance.Attendance;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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



//    @OneToMany(mappedBy="course", cascade = {CascadeType.REMOVE})
//    private List<THomework> tHomeworks;
//
//    @ManyToOne
//    @JoinColumn(name="teacher_id")
//    private Teacher teacher;



}
