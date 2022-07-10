package com.example.nf.newfine_backend;


import com.example.nf.newfine_backend.attendance.Attendance;
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

//    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST})
////    private List<Attendance> attendances;

//    @OneToMany(mappedBy="course", cascade = {CascadeType.REMOVE})
//    private List<THomework> tHomeworks;
//
//    @ManyToOne
//    @JoinColumn(name="teacher_id")
//    private Teacher teacher;



}
