package com.example.nf.newfine_backend.study;


import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.study.Study;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StudentStudy {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "sstudy_id")
    private Long sstudyId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST})
    @JsonManagedReference
    private Study study;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE})
    @JsonManagedReference
    private Student student;


    @Column
    private boolean isIn = false;
    @Column
    private boolean isOut = false;

    // 자습 총 시간
    @Column
    private long total;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime startTime;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime endTime;


    @Builder
    public StudentStudy(Student student,Study study,LocalDateTime startTime,Boolean isIn,Boolean isOut) {
        this.student=student;
        this.study=study;
        this.startTime=startTime;
        this.isIn=isIn; // 입장
        this.isOut=isOut; //퇴실

    }




}
