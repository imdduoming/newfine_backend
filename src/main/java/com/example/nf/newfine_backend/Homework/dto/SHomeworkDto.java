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

    private String name;
    private String title;

    private char grade;

    private boolean ischecked;

    private String createdDate;

    private LocalDateTime checkedDate;


    public static SHomeworkDto toDto(SHomework sHomework) {
        return new SHomeworkDto(
                sHomework.getShid(),
                sHomework.getListener().getStudent().getName(),
                sHomework.getTitle(),
                sHomework.getGrade(),
                sHomework.isIschecked(),
                sHomework.getCreatedDate(),
                sHomework.getCheckedDate()
        );
    }
}

/*sHomework.getListener().getStudent().getName()*/
