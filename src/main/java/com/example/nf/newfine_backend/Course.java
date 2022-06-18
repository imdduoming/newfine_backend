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
public class Course {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "course_id")
    private Long cId;

    @Column(nullable = false)
    private String cName;


    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Student> students;

    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    private List<THomework> tHomeworks;

    @OneToOne
    private Teacher teacher;



}
