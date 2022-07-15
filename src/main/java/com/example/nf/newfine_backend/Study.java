package com.example.nf.newfine_backend;

import com.example.nf.newfine_backend.student.domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Study extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "study_id")
    private Long ssId;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Student student;

    @Column(nullable = false)
    private Date entranceTime;

    @Column(nullable = false)
    private Date endTime;


}
