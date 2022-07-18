package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE})
    private Course course;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE})
    private Student student;


}
