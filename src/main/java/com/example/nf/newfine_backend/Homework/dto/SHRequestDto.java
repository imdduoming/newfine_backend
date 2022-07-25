package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;

public class SHRequestDto {

    private Long id;
    private String comment;
    private THomework tHomework;
    private String origFilename;
    private String filename;
    private String filePath;


    /* Dto -> Entity */
    public SHomework toEntity() {
        SHomework sHomeworks = SHomework.builder()
                .id(id)
                .comment(comment)
                .tHomework(tHomework)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return sHomeworks;
    }

}
