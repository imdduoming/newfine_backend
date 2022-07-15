package com.example.nf.newfine_backend.Homework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class THomework extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ThId")
    private Long id;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ThName")
    private String writer; //어케 해야 할지..


    @Column(length = 40, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String filePath;

    private int count;

    //private LocalDateTime createdDate = LocalDateTime.now();

    //private LocalDateTime modifiedDate;


    //== 게시글을 삭제하면 달려있는 과제 이미지파일 모두 삭제 ==//
//    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
//    private List<SHomework> homeworkList = new ArrayList<>();


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
    public THomework(String title, String content, String writer, int count, String filePath) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.count = count;
        this.filePath = filePath;
    }

    public void update(String title, String content, String writer, String filePath) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        //this.modifiedDate = LocalDateTime.now();
        this.filePath = filePath;
    }
}

