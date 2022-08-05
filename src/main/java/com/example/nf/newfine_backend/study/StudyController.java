package com.example.nf.newfine_backend.study;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceDto;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.dto.StudentAttendanceDto;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class StudyController {
    private final StudentRepository studentRepository;
    private final StudyService studyService;
    private final StudentStudyRepository studentStudyRepository;

    // 관리자가 수업시간 qr 코드 생성 api
    @PostMapping  (value = "/make/study" )
    public Study makeStudy(@RequestBody StudyDto studyDto) {
        LocalDateTime startTime=studyDto.getStartTime();
        System.out.println(studyDto.getStartTime());
        return  studyService.makeStudy(studyDto.getStartTime());

    }
    // 학생 쟈습 enter api
    @PostMapping  (value = "/study/start" )
    public int enterStudy(@RequestBody StudentStudyDto studentStudyDto, @RequestHeader HttpHeaders headers) {
        Long study_id=Long.parseLong(studentStudyDto.getStudyId());
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        int ans=studyService.enterStudy(study_id,student);
        System.out.println(ans);
        return ans; //제대로 된 입실은 1
        // 출석하고 앱 화면으로 돌리기
    }

    // 학생 자습 퇴실 api
    @PutMapping  (value = "/study/end" )
    public int endStudy(@RequestBody StudentStudyDto studentStudyDto, @RequestHeader HttpHeaders headers) {
        Long study_id=Long.parseLong(studentStudyDto.getStudyId());
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        int ans=studyService.endStudy(study_id,student);
        System.out.println(ans);
        return ans;//제대로 된 퇴실은 1
        // 출석하고 앱 화면으로 돌리기
    }

    @GetMapping("/study/my")
    public List<StudentStudy> getMyStudy(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return studyService.getMyStudy(student);
    }

    @GetMapping("/study/total")
    public long getTotal(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        List<StudentStudy> studentStudyList= studentStudyRepository.findStudentStudiesByStudent(student);
        return studyService.totalMyStudy(studentStudyList);
    }




}
