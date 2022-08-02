package com.example.nf.newfine_backend.Homework.dto;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SHomeworkDto {

    private Long shId;

    private String name;
    private String comment;

    private String createdDate;

    private String modifiedDate;

    public static SHomeworkDto toDto(SHomework sHomework) {
        return new SHomeworkDto(
                sHomework.getShid(),
                sHomework.getListener().getStudent().getName(),
                sHomework.getComment(),
                sHomework.getCreatedDate(),
                sHomework.getModifiedDate()
        );
    }
}

/*sHomework.getListener().getStudent().getName()*/
