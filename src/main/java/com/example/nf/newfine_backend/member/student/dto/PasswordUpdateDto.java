package com.example.nf.newfine_backend.member.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateDto {
    private String phoneNumber;
//    private String password;
//    private String passwordChk;
    private String newPassword;
}
