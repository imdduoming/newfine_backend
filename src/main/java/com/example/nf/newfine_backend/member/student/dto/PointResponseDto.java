package com.example.nf.newfine_backend.member.student.dto;

import com.example.nf.newfine_backend.member.student.domain.Point;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PointResponseDto {
    private Long pointId;
    private String contents;
    private LocalDateTime date;
    private int score;
    private int scoreSum;

    public static PointResponseDto convertToPointDto(Point point) {
        return PointResponseDto.builder()
                .pointId(point.getId())
                .contents(point.getContents())
                .date(point.getDatetime())
                .score(point.getScore())
                .scoreSum(0)
                .build();
    }
}
