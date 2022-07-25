package com.example.nf.newfine_backend.Homework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SHomework extends BaseTimeEntity{
    @Id
    @Column(name = "ShId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String comment; // 댓글 내용 (내용이 없어도 됨)

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;
    // 멀티파일 업로드 가능하게 바꿔야 함

    @ManyToOne
    @JoinColumn(name = "ThId")
    private THomework tHomework;

    // @ManyToOne
    // @JoinColumn(name = "student_id")
    //private Student student; 리스너 엔티티랑 연결하라는데..

    @Builder
    public SHomework(Long id, String comment, String origFilename, String filename, String filePath) {
        this.id = id;
        this.comment = comment;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
