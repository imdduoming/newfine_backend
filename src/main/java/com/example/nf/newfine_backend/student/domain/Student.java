package com.example.nf.newfine_backend.student.domain;

import com.example.nf.newfine_backend.attendance.StudentAttendance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name="member")
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
    private int point;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy="student", cascade = { CascadeType.PERSIST})
    private List<StudentAttendance> studentAttendancces;


    @Column
    private LocalDateTime signupDate;

    @PrePersist
    public void signupDate() {
        this.signupDate = LocalDateTime.now();
    }

    @Builder
    public Student(String phoneNumber, String name, String password, String nickname, Authority authority, String photoURL, int point) {
        this.phoneNumber=phoneNumber;
        this.name=name;
        this.password = password;
        this.authority = authority;
        this.nickname= nickname;
        this.photoURL=photoURL;
        this.point=point;
    }
}
