package com.example.nf.newfine_backend.teacher;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.student.domain.Authority;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long tId;

    @Column
    private String phoneNumber;


    @Column(nullable = false)
    private String tPassword;

    @Column(nullable = false)
    private String tName;

    @Enumerated(EnumType.STRING)
    private Authority tAuthority;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="teacher")
    private List<Course> courses;

//    @JsonIgnore
//    @OneToMany(mappedBy="teacher", cascade = { CascadeType.PERSIST})
//    private List<THomework> tHomeworks;


    @Builder
    public Teacher(String phoneNumber, String tName, String tPassword, Authority tAuthority) {
        this.phoneNumber=phoneNumber;
        this.tName=tName;
        this.tPassword = tPassword;
        this.tAuthority = tAuthority;
    }

}
