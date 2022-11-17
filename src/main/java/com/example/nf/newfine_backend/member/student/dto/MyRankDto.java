package com.example.nf.newfine_backend.member.student.dto;

import com.example.nf.newfine_backend.member.student.domain.Tier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyRankDto {
    private int myRank;
    private Tier myTier;
}
