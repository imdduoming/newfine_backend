package com.example.nf.newfine_backend.member.student.domain;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.branch.domain.Branch;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.domain.Timestamped;
import com.example.nf.newfine_backend.study.StudentStudy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends Timestamped {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String phoneNumber;

    @Column
    private String name;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String photoURL;

    @Column
    @ColumnDefault("0")
    private Integer point;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy="student", cascade = { CascadeType.REMOVE})
    @JsonBackReference //순환참조 방지
    private List<StudentAttendance> studentAttendancces;

    @OneToMany(mappedBy="student", cascade = { CascadeType.REMOVE})
    @JsonBackReference //순환참조 방지
    private List<StudentStudy> studentStudies;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="student", cascade = { CascadeType.REMOVE})
    private List<Listener> listeners;

    @Column
    @Enumerated(EnumType.STRING)
    private Level level=Level.NEW;

    private LocalDateTime levelUpDate;


    @Column
    private LocalDateTime signupDate;

    @PrePersist
    public void signupDate() {
        this.signupDate = LocalDateTime.now();
    }

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="owner", orphanRemoval = true, cascade = CascadeType.REMOVE)  // 주체는 Point 객체
    private List<Point> pointList=new ArrayList<>();

//    @JsonManagedReference
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
//    private Branch branch;

    @Builder
    public Student(String phoneNumber, String name, String password, String nickname, Authority authority, String photoURL, Integer point) {
        this.phoneNumber=phoneNumber;
        this.name=name;
        this.password = password;
        this.authority = authority;
        this.nickname= nickname;
        this.photoURL=photoURL;
        this.point=point;
//        this.branch=branch;
    }

    public boolean availableLevelUp() {
        return Level.availableLevelUp(this.getLevel(), this.getPoint());
    }

    public Level levelUp() {
        Level nextLevel = Level.getNextLevel(this.getPoint());
        this.level = nextLevel;
        this.levelUpDate = LocalDateTime.now();
        System.out.println(level);
        System.out.println(point);
        return nextLevel;
    }
}
