package com.example.nf.newfine_backend.Homework.domain;

import com.example.nf.newfine_backend.Homework.dto.THomeworkDto;
import com.example.nf.newfine_backend.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class THomework extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "th_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonManagedReference
    private Course course; // 코스 별로 여러 과제 가질 수 있음


    @Column(name = "first_deadline")
    private String fdeadline = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Column(name = "second_deadline")
    private String sdeadline = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Column(length = 40, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;


    private int count;



    //== 게시글을 삭제하면 달려있는 과제 이미지파일 모두 삭제 ==//
    @JsonBackReference
    @OneToMany(mappedBy = "thomework", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("shid desc") // 과제 순서 정렬 (최신 꺼 순으로)
    private List<SHomework> sHomeworks;


    @Builder
    public THomework(THomeworkDto tHomeworkDto, Course course) {
        this.title = tHomeworkDto.getTitle();
        this.content = tHomeworkDto.getContent();
        this.course = course;
        this.fdeadline = tHomeworkDto.getFdeadline();
        this.sdeadline = tHomeworkDto.getSdeadline();
        //this.count = count;
    }

    public void update(String title, String content, String fdeadline, String sdeadline) {
        this.title = title;
        this.content = content;
        this.fdeadline = fdeadline;
        this.sdeadline = sdeadline;
        //this.modifiedDate = LocalDateTime.now();
    }
}

