package com.example.nf.newfine_backend.Homework.domain;

import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.course.Listener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String comment; // 댓글 내용 (내용이 없어도 됨)

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "th_id")
    private THomework thomework;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "listener_id")
    private Listener listener;


    @Builder
    public SHomework(SHomeworkDto sHomeworkDto, THomework tHomework, Listener listener) {
        this.thomework = tHomework;
        this.listener = listener;
        this.comment = sHomeworkDto.getComment();
    }
}
