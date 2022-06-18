package com.example.nf.newfine_backend;

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
public class Student {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "student_id")
    private Long sId;

    @Column(nullable = false)
    private String sPassword;

    @Column(nullable = false)
    private String sNickname;

    @Column(nullable = false)
    private String sName;

    @Column(nullable = false)
    private int sTotalScore;

    @Column(nullable = false)
    private int sGrade;

    @Column(nullable = false)
    private boolean sPushAlarm;

    @Column(nullable = false)
    private String sPhoneNumber;

    @JoinColumn(name = "course_id")
    @OneToMany
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy="student", cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    private List<SHomework> sHomeworks;

    @Column(nullable = false)
    private int totalStudyTime;




}
