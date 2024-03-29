package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SHomeworkDto {

    private Long shId;

    private Long thId;

    private String course;

    private Long studentId;

    private String name;
    private String title;

    private String deadline;

    private boolean ischecked;

    private LocalDateTime modifiedDate;


    public static SHomeworkDto toDto(SHomework sHomework) {
        return new SHomeworkDto(
                sHomework.getShid(),
                sHomework.getThomework().getId(),
                sHomework.getThomework().getCourse().getCName(),
                sHomework.getListener().getStudent().getId(),
                sHomework.getListener().getStudent().getName(),
                sHomework.getTitle(),
                sHomework.getDeadline(),
                sHomework.isIschecked(),
                sHomework.getModifiedDate()
        );
    }
}

/*sHomework.getListener().getStudent().getName()*/
