package com.example.nf.newfine_backend.teacher;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.student.domain.Authority;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Teacher {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "teacher_id")
    private Long tId;

    @Column(nullable = false)
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
