package com.example.nf.newfine_backend.student.domain;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Listener;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @Builder
    public Student(String phoneNumber, String name, String password, String nickname, Authority authority, String photoURL, Integer point) {
        this.phoneNumber=phoneNumber;
        this.name=name;
        this.password = password;
        this.authority = authority;
        this.nickname= nickname;
        this.photoURL=photoURL;
        this.point=point;
    }

    public boolean availabelLevelUp() {
        return Level.availabelLevelUp(this.getLevel(), this.getPoint());
    }

    public Level levelUp() {
        Level nextLevel = Level.getNextLevel(this.getPoint());
        this.level = nextLevel;
        this.levelUpDate = LocalDateTime.now();

        return nextLevel;
    }
}
