package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;

public class SHResponseDto {

    private Long id;
    private String comment;
    private THomework tHomework;
    private String origFilename;
    private String filename;
    private String filePath;
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일

    private Long ThId;

    public SHResponseDto(SHomework sHomework) {
        this.id = sHomework.getId();
        this.comment = sHomework.getComment();
        this.origFilename = sHomework.getOrigFilename();
        this.filename = sHomework.getFilename();
        this.filePath = sHomework.getFilePath();
        this.createdDate = sHomework.getCreatedDate();
        this.modifiedDate = sHomework.getModifiedDate();
        this.ThId = sHomework.getTHomework().getId();

    }
}
