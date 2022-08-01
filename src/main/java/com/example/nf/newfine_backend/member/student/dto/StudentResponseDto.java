package com.example.nf.newfine_backend.member.student.dto;

import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private String phoneNumber;
    private String nickname;
    private String photoURL;

    public static StudentResponseDto of(Student student) {
        return new StudentResponseDto(student.getPhoneNumber(), student.getNickname(), student.getPhotoURL());
    }
}
