package com.example.nf.newfine_backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    private Long course_id;
    private String testName;
    private LocalDateTime testDate;
}
