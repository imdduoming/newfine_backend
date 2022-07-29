package com.example.nf.newfine_backend.student.dto;

import com.example.nf.newfine_backend.student.domain.Authority;
import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String phoneNumber;
    private String name;
    private String password;

    public Student toMember(PasswordEncoder passwordEncoder) {
        return Student.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public Teacher toTeacher(PasswordEncoder passwordEncoder) {
        return Teacher.builder()
                .tPhoneNumber(phoneNumber)
                .tName(name)
                .tPassword(passwordEncoder.encode(password))
                .tAuthority(Authority.ROLE_ADMIN)
                .build();
    }
}
