package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Course;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private Course course; // 작성자
    private int count; // 조회 수
    //private char deleteYn; // 삭제 여부
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일


    public ResponseDto(THomework entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.course = entity.getCourse();
        this.count = entity.getCount();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        // 엔티티간 무한참조 방지
    }

}
