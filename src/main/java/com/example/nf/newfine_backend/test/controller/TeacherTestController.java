package com.example.nf.newfine_backend.test.controller;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.member.teacher.repository.TeacherRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.TeacherTestResultDto;
import com.example.nf.newfine_backend.test.dto.teacher.TeacherTypeResultDto;
import com.example.nf.newfine_backend.test.service.TeacherTestService;
import com.example.nf.newfine_backend.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherTestController {
    private final TeacherRepository teacherRepository;
    private final TestService testService;
    private final TeacherTestService teacherTestService;


    @GetMapping("/test/course/teacher")
    public List<Test> getTeacherTests(@RequestParam Integer id){
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long idx = Long.valueOf(id);
        return testService.getTests(idx);
    }
    @GetMapping("/test/result/teacher")
    public TeacherTestResultDto getTeacherTestResults(@RequestParam Integer id){
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long test_id = Long.valueOf(id);
        return teacherTestService.getTeacherTestResults(teacher,test_id);
    }

    // 킬러 / 준킬러 문항 분석
    @GetMapping("/test/result/type/teacher")
    public TeacherTypeResultDto getTeacherTypeResults(@RequestParam Integer id){
        Teacher teacher=teacherRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long test_id = Long.valueOf(id);
        return teacherTestService.getTeacherTypeResults(teacher,test_id);
    }



}
