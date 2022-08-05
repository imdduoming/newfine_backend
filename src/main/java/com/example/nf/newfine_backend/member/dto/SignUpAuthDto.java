package com.example.nf.newfine_backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpAuthDto {
    private String phoneNumber;
    private String branch;
}
