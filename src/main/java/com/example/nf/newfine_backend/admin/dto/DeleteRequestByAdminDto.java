package com.example.nf.newfine_backend.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRequestByAdminDto {
    private String phoneNumber;
    private String password;
}
