package com.example.nf.newfine_backend.member.teacher.controller;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceEditDto;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseService;
import com.example.nf.newfine_backend.member.student.dto.StudentResponseDto;
import com.example.nf.newfine_backend.member.teacher.dto.TeacherResponseDto;
import com.example.nf.newfine_backend.member.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    //선생님이 자신 강의 불러오기
    @GetMapping("/teacher/courses")
    public List<Course> getTeacherCourses(){
        return teacherService.getTeacherCourses();
    }

    // 선생님 출석 변경 api
    @PutMapping("/teacher/attendance")
    public StudentAttendance editAttendance(@RequestBody AttendanceEditDto attendanceEditDto){
        Long id=Long.valueOf(attendanceEditDto.getId());
        return teacherService.editAttendance(id,attendanceEditDto.getState());
    }

    @GetMapping("/member/teacher")
    public ResponseEntity<TeacherResponseDto> getTeacherInfo() {
        return ResponseEntity.ok(teacherService.getTeacherInfo());
    }

}

