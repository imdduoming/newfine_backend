package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.THomework;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveRequestDto {

    private String title;
    private String content;
    private String writer;

    public THomework toEntity() {
        return THomework.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .count(0)
                .build();
    }
}
