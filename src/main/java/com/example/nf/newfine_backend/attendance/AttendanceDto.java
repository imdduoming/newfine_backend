package com.example.nf.newfine_backend.attendance;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private String studentName;
    private String phoneNumber;
}
