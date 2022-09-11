package com.example.nf.newfine_backend.member.dto;

import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.service.AuthService;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SignUpDto {
    private String phoneNumber;
    private String name;
    private String password;

    private String deviceToken;


    public Student toMember(PasswordEncoder passwordEncoder) {
        return Student.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .deviceToken(deviceToken)
                .build();
    }

    public Teacher toTeacher(PasswordEncoder passwordEncoder) {
        return Teacher.builder()
                .phoneNumber(phoneNumber)
                .tName(name)
                .tPassword(passwordEncoder.encode(password))
                .tAuthority(Authority.ROLE_ADMIN)
                .build();
    }
}
