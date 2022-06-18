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
public class Teacher {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "teacher_id")
    private Long tId;

    @Column(nullable = false)
    private String tPassword;

    @Column(nullable = false)
    private String tName;

    @Column(nullable = false)
    private String tPhoneNumber;

    @JoinColumn(name = "course_id")
    @OneToMany
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy="teacher", cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    private List<THomework> tHomeworks;



}
