package com.example.nf.newfine_backend.Homework.dto;


import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class THomeworkDto {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private Course course; // 작성자
    //private int count; // 조회 수
    //private char deleteYn; // 삭제 여부
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일


    public static THomeworkDto toDto(THomework tHomework) {
        return new THomeworkDto(
                tHomework.getId(),
                tHomework.getTitle(),
                tHomework.getContent(),
                tHomework.getCourse(),
                tHomework.getCreatedDate(),
                tHomework.getModifiedDate()
        );
    }
}
