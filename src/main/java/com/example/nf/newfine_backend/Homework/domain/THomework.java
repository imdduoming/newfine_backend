package com.example.nf.newfine_backend.Homework.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class THomework extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "th_id")
    private Long id;


    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ThName")
    private String writer; //어케 해야 할지..


    @Column(length = 40, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;


    private int count;

    //private LocalDateTime createdDate = LocalDateTime.now();

    //private LocalDateTime modifiedDate;


    //== 게시글을 삭제하면 달려있는 과제 이미지파일 모두 삭제 ==//
    @JsonBackReference
    @OneToMany(mappedBy = "thomework", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("shid desc") // 과제 순서 정렬 (최신 꺼 순으로)
    private List<SHomework> sHomeworks;


    //== 연관관계 편의 메서드 ==//
//    public void confirmWriter(String writer) {
//        writer는 변경이 불가능하므로 이렇게만 해주어도 될듯
//        this.writer = writer;
//        writer.addPost(this);
//    }

//    public void addHomework(SHomework sHomework) {
//        comment의 Post 설정은 comment에서 함
//        sHomeworkList.add(sHomework);
//    }

    @Builder
    public THomework(String title, String content, String writer, int count) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.count = count;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        //this.modifiedDate = LocalDateTime.now();
    }
}

