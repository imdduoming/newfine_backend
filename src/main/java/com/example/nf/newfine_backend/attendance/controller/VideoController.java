package com.example.nf.newfine_backend.attendance.controller;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceDto;
import com.example.nf.newfine_backend.attendance.dto.ParentDto;
import com.example.nf.newfine_backend.attendance.dto.StudentAttendanceDto;
import com.example.nf.newfine_backend.attendance.dto.VideoApplyDto;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.service.VideoService;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.MessageService;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.MEMBER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class VideoController {
    private final AttendanceService attendanceService;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final MessageService messageService;
    private final VideoService videoService;

    //동영상 신청을 위해 현재 출석이 생긴 수업을 불러오는 api
    @GetMapping("/attendances/my/now")
    public List<Attendance> getNowAttendance() {
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return videoService.getNowAttendance(student);
    }

    @PostMapping("/sendMessage/parents")
    public ResponseEntity<String> sendMessage(@RequestBody ParentDto parentDto) {
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        int randomNumber=(int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

        // 가입된 회원인지 확인
        if (!studentRepository.existsByPhoneNumber(parentDto.getPhoneNumber())) {
            throw new CustomException(MEMBER_NOT_FOUND);
        }
        return ResponseEntity.ok(messageService.sendMessage(parentDto.getPhoneNumber(), String.valueOf(randomNumber)));
    }

    // 동영산 신청 api
    @PutMapping("/apply/video")
    public StudentAttendance applyVideo(@RequestBody VideoApplyDto videoApplyDto){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long id=Long.valueOf(videoApplyDto.getId());
        return videoService.applyVideo(id,student);
    }


    //동영상 신청을 위해 현재 출석이 생긴 수업을 불러오는 api
    @GetMapping("/number/parents")
    public String getParentsNumber() {
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return videoService.getParentsNumber(student);
    }


}
