package com.example.nf.newfine_backend.member.student.dto;

import com.example.nf.newfine_backend.member.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRankingDetailDto {
    private String phoneNumber;
    private String nickname;
    private String name;

    public static StudentRankingDetailDto of(Student student) {
        return new StudentRankingDetailDto(student.getPhoneNumber(), student.getNickname(), student.getName());
    }
}
