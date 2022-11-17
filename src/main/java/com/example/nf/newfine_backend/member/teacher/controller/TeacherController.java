package com.example.nf.newfine_backend.member.teacher.controller;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceEditDto;
import com.example.nf.newfine_backend.attendance.dto.VideoEditDto;
import com.example.nf.newfine_backend.attendance.dto.VideoReturnDto;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.service.VideoService;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.member.teacher.dto.TeacherResponseDto;
import com.example.nf.newfine_backend.member.teacher.repository.TeacherRepository;
import com.example.nf.newfine_backend.member.teacher.service.TeacherService;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;
    private final AttendanceService attendanceService;
    private final VideoService videoService;

    //선생님이 자신 강의 불러오기
    @GetMapping("/teacher/courses")
    public List<Course> getTeacherCourses(){
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return teacherService.getTeacherCourses(teacher);
    }

    // 선생님 출석 변경 api
    @PutMapping("/teacher/attendance")
    public StudentAttendance editAttendance(@RequestBody AttendanceEditDto attendanceEditDto){
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long id=Long.valueOf(attendanceEditDto.getId());
        return teacherService.editAttendance(id,attendanceEditDto.getState());
    }

    @GetMapping("/member/teacher")
    public ResponseEntity<TeacherResponseDto> getTeacherInfo() {
        return ResponseEntity.ok(teacherService.getTeacherInfo());
    }

    // 매 수업시간 마다 출석 현황
    @GetMapping("/attendances/student")
    public List<StudentAttendance> getStudentAttendance(@RequestParam Integer id) {
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long idx = Long.valueOf(id);
        return attendanceService.getStudentAttendance(idx);
    }


    // 수업시간마다 매시간 출석부 가져오는 api , 출석 정보는 attendance 의 Student Attendance 로 가져오면 된다 .
    @GetMapping("/attendances")
    public List<Attendance> getAttendances(@RequestParam Integer id) {
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long idx = Long.valueOf(id);
        return attendanceService.getAttendances(idx);
    }

    // 선생님이 비디오 신청 현황
    @GetMapping("/video/list")
    public List<VideoReturnDto> getVideos() {
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return videoService.getVideos(teacher);
    }

    @PutMapping("/video/ok")
    public StudentAttendance editVideo(@RequestBody VideoEditDto videoEditDto) throws IOException {
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long id=Long.valueOf(videoEditDto.getId());
        return videoService.editVideo(id);
    }

}


