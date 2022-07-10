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
public class Student {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "student_id")
    private Long sId;

    @Column(nullable = false)
    private String spassword;

    @Column(nullable = false)
    private String snickname;

    @Column(nullable = false)
    private String sname;

    @Column(nullable = false)
    private int stotalscore;

    @Column(nullable = false)
    private int sgrade;

    @Column(nullable = false)
    private boolean spushAlarm;

    @Column(nullable = false)
    private String sphoneNumber;

//
//    @OneToMany(mappedBy="student", cascade = { CascadeType.PERSIST})
//    private List<Attendance> attendances;

//    @JsonIgnore
//    @OneToMany(mappedBy="student", cascade = {CascadeType.REMOVE})
//    private List<SHomework> sHomeworks;

    @Column(nullable = false)
    private int totalStudyTime;

}
