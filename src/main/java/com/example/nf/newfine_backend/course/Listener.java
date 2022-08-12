package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Listener {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "listener_id")
    private Long Id;

    @JsonManagedReference
    @ManyToOne( cascade = { CascadeType.REMOVE})
    private Course course;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE})
    private Student student;

//    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST})
//    @JsonBackReference //순환참조 방지
//    private List<StudentTestResults> studentTestResults;


}
