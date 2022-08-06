package com.example.nf.newfine_backend.attendance.controller;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceDto;
import com.example.nf.newfine_backend.attendance.dto.AttendanceEditDto;
import com.example.nf.newfine_backend.attendance.dto.VideoApplyDto;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.dto.StudentAttendanceDto;
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

    // 수업시간마다 매시간 출석부 가져오는 api , 출석 정보는 attendance 의 Student Attendance 로 가져오면 된다 .
    @GetMapping("/attendances")
    public List<Attendance> getAttendances(@RequestParam Integer id) {
        Long idx = Long.valueOf(id);
        return attendanceService.getAttendances(idx);
    }

    // 매 수업시간 마다 출석 현황
    @GetMapping("/attendances/student")
    public List<StudentAttendance> getStudentAttendance(@RequestParam Integer id) {
        Long idx = Long.valueOf(id);
        return attendanceService.getStudentAttendance(idx);
    }


    @GetMapping("/attendances/my")
    public List<StudentAttendance> getMyAttendance(@RequestParam Integer id) {
        Long idx = Long.valueOf(id);
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return attendanceService.getMyAttendance(idx,student);
    }

    //동영상 신청을 위해 현재 출석이 생긴 수업을 불러오는 api
    @GetMapping("/attendances/my/now")
    public List<Attendance> getNowAttendance() {
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return attendanceService.getNowAttendance(student);
    }

//    @PostMapping("/sendMessage/parents")
//    public ResponseEntity<String> sendMessage(@RequestBody PhoneNumberDto phoneNumberDto) {
//        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
//        int randomNumber=(int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
//
//        // 가입된 회원인지 확인
//        if (!studentRepository.existsByPhoneNumber(phoneNumberDto.getPhoneNumber())) {
//            throw new CustomException(MEMBER_NOT_FOUND);
//        }
//        return ResponseEntity.ok(messageService.sendMessage(phoneNumberDto.getPhoneNumber(), String.valueOf(randomNumber)));
//    }

    // 동영산 신청 api
    @PutMapping("/apply/video")
    public StudentAttendance applyVideo(@RequestBody VideoApplyDto videoApplyDto){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long id=Long.valueOf(videoApplyDto.getId());
        return attendanceService.applyVideo(id,student);
    }

}


