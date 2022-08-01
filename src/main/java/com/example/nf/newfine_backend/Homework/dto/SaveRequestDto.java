package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Course;
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

    private Course course;

    public THomework toEntity() {
        return THomework.builder()
                .title(title)
                .content(content)
                .course(course)
                .build();
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
