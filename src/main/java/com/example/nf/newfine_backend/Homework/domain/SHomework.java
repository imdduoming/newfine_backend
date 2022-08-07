package com.example.nf.newfine_backend.Homework.domain;

import com.example.nf.newfine_backend.course.Listener;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "listener_id")
    @JsonManagedReference
    private Listener listener;

    @Column
    private boolean ischecked = false;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime checkedDate;


    @Builder
    public SHomework(String title, THomework tHomework, Listener listener, Boolean ischecked, LocalDateTime checkedDate) {
        this.title = title;
        this.thomework = tHomework;
        this.listener = listener;
        this.ischecked = ischecked;
        this.checkedDate = checkedDate;
    }
}

