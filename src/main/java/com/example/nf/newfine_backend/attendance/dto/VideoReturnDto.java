package com.example.nf.newfine_backend.attendance.dto;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoReturnDto {
    // 출석 id
    Course course;
    StudentAttendance studentAttendance;
    Attendance attendance;
    Student student;

}
