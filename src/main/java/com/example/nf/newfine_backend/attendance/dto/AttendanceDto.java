package com.example.nf.newfine_backend.attendance.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private Long course_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
