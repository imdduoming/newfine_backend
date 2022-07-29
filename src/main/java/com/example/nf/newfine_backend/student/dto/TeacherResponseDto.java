package com.example.nf.newfine_backend.student.dto;

import com.example.nf.newfine_backend.student.domain.Authority;
import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponseDto {
    private String phoneNumber;
    private String name;
    private Authority authority;

    public static TeacherResponseDto of(Teacher teacher) {
        return new TeacherResponseDto(teacher.getTPhoneNumber(), teacher.getTName(), teacher.getTAuthority());
    }
}
