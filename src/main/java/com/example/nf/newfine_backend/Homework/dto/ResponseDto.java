package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.THomework;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {

    private Long th_id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int count; // 조회 수
    //private char deleteYn; // 삭제 여부
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일

    private List<SHomeworkDto> sHomeworks;

    public ResponseDto(THomework entity) {
        this.th_id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.count = entity.getCount();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        //this.sHomeworks = entity.getSHomeworks().stream().map(SHomeworkDto::new).collect(Collectors.toList());
        // 엔티티간 무한참조 방지
    }

}
