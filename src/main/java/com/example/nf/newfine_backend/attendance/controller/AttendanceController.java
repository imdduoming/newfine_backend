package com.example.nf.newfine_backend.attendance.controller;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.*;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.dto.PhoneNumberDto;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.MessageService;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.MEMBER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final MessageService messageService;

    // 관리자가 수업시간 qr 코드 생성 api
    @PostMapping(value = "/make/attendance")
    public Attendance makeAttendance(@RequestBody AttendanceDto attendanceDto) {
        LocalDateTime startTime = attendanceDto.getStartTime();
        LocalDateTime endTime = attendanceDto.getEndTime();
        System.out.println(attendanceDto.getStartTime());
        return attendanceService.makeAttendance(attendanceDto.getCourse_id(), attendanceDto.getStartTime(), attendanceDto.getEndTime());

    }

    // 학생 출석 api
    @PostMapping(value = "/add/attendance")
    public int addAttendance(@RequestBody StudentAttendanceDto studentAttendanceDto) {
        Long attendance_id = Long.parseLong(studentAttendanceDto.getAttendance_id());
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        int ans = attendanceService.addAttendance(attendance_id, student);
        System.out.println(ans);
        return ans;
    }

    @GetMapping("/attendances/all")
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }


    @GetMapping("/attendances/my")
    public List<StudentAttendance> getMyAttendance(@RequestParam Integer id) {
        Long idx = Long.valueOf(id);
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return attendanceService.getMyAttendance(idx,student);
    }

}


