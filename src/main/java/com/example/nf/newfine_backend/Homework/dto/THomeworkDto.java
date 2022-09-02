package com.example.nf.newfine_backend.Homework.dto;


import com.example.nf.newfine_backend.Homework.domain.THomework;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class THomeworkDto {

    private Long id; // PK
    private String title; // 제목
    private String name;
    private String content; // 내용
    //private int count; // 조회 수
    //private char deleteYn; // 삭제 여부
    private LocalDateTime createdDate; // 생성일
    private LocalDateTime modifiedDate; // 수정일

    private String fdeadline;
    private String sdeadline;


    public static THomeworkDto toDto(THomework tHomework) {
        return new THomeworkDto(
                tHomework.getId(),
                tHomework.getTitle(),
                tHomework.getCourse().getTeacher().getTName(), //추가
                tHomework.getContent(),
                tHomework.getCreatedDate(),
                tHomework.getModifiedDate(),
                tHomework.getFdeadline(),
                tHomework.getSdeadline()
        );
    }
}
