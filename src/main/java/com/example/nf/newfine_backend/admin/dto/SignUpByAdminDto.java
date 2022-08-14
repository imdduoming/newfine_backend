package com.example.nf.newfine_backend.admin.dto;

import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpByAdminDto {
//    private Branch branch;
    private String name;
    private String phoneNumber;
    private String nickname;
    private String password;

    public Student toMember(PasswordEncoder passwordEncoder) {
        return Student.builder()
//                .branch(branch)
                .phoneNumber(phoneNumber)
                .name(name)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }
}
