package com.example.nf.newfine_backend.test.controller;


import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import com.example.nf.newfine_backend.test.dto.MyAllTestDto;

import com.example.nf.newfine_backend.test.dto.student.TestResultDto;
import com.example.nf.newfine_backend.test.dto.student.TypeResultDto;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import com.example.nf.newfine_backend.test.service.TestResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TestResultController {

    private final TestResultService testResultService;
    private final StudentRepository studentRepository;
    private final TestRepository testRepository;

    // 내 테스트 결과분석 / 오답룰 best 5
    @GetMapping("/test/result/my")
    public TestResultDto getTestResults(@RequestParam Integer id){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long test_id = Long.valueOf(id);
        return testResultService.getTestResults(student,test_id);
    }

    // 킬러 / 준킬러 문항 분석
    @GetMapping("/test/result/type")
    public TypeResultDto getTypeResults(@RequestParam Integer id){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long test_id = Long.valueOf(id);
        return testResultService.getTypeResults(student,test_id);
    }

    @GetMapping("/test/result/all/my")
    public List<MyAllTestDto> getAllMyResults(@RequestParam Integer id){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long test_id = Long.valueOf(id);
        return testResultService.getAllMyTests(student,test_id);
    }



}
