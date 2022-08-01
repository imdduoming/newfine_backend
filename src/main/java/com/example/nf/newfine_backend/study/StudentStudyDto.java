package com.example.nf.newfine_backend.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentStudyDto {
    private String studyId;
    private String content; // 입장인지 퇴실인지
}
