package com.example.nf.newfine_backend.student.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Data
@NoArgsConstructor
public class RankingResponseDto {
    private String nickname;
    private int score;

    public static RankingResponseDto convertToRankingResponseDto(ZSetOperations.TypedTuple typedTuple){
        RankingResponseDto responseRankingDto=new RankingResponseDto();
        responseRankingDto.nickname=typedTuple.getValue().toString();
        responseRankingDto.score=typedTuple.getScore().intValue();
        return responseRankingDto;
    }
}
