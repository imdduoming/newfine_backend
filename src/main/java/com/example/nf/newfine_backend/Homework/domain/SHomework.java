package com.example.nf.newfine_backend.Homework.domain;

import com.example.nf.newfine_backend.course.Listener;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

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

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "listener_id")
    @JsonManagedReference
    private Listener listener;

    //@Column
    //private boolean check = false;


    @Builder
    public SHomework(String title, THomework tHomework, Listener listener, Boolean check) {
        this.title = title;
        this.thomework = tHomework;
        this.listener = listener;
        //this.check = check;
    }
}

