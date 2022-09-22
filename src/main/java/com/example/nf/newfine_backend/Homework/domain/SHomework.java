package com.example.nf.newfine_backend.Homework.domain;

import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.course.Listener;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class SHomework extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sh_id")
    private Long shid;

    @Column(columnDefinition = "TEXT")
    private String title; // thomework과 동일한 제목 (내용이 없어도 됨)

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "th_id")
    @JsonManagedReference
    private THomework thomework;

    @ManyToOne(fetch = EAGER,cascade = { CascadeType.REMOVE})
    @JoinColumn(name = "listener_id")
    @JsonManagedReference
    private Listener listener;

    @Column
    private boolean ischecked = false;

    //    @Column
//    private char grade = 'D';
    @Column
    private String deadline = "미제출";

    @Column
    private Long studentId;


    @Builder
    public SHomework(SHomeworkDto sHomeworkDto, THomework tHomework, Listener listener) {
        this.title = sHomeworkDto.getTitle();
        this.thomework = tHomework;
        this.listener = listener;
        this.ischecked = sHomeworkDto.isIschecked();
        this.deadline = sHomeworkDto.getDeadline();
        this.studentId = sHomeworkDto.getStudentId();
    }

//    public void update(Boolean ischecked, char grade) {
//        this.ischecked = ischecked;
//        this.grade = grade;
    //this.modifiedDate = LocalDateTime.now();
//    }
}
