package com.example.nf.newfine_backend.test.controller;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.TestDto;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import com.example.nf.newfine_backend.test.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class TestController {

    private final TestService testService;
    private final StudentRepository studentRepository;
    private final TestRepository testRepository;

    @ResponseBody
    @RequestMapping(value = "/admin/makeTestForm.do", method = RequestMethod.POST)
    public Test createTest(HttpServletRequest request, TestDto testDto) throws Exception{
        testDto.setTestDate(LocalDate.parse(request.getParameter("test_date")));
        testDto.setTestName(request.getParameter("test_name"));
        testDto.setCourse_id(Long.valueOf(request.getParameter("course_id")));
        return testService.createTest(testDto);
    }

    // 관리자가 준킬러문항 / 킬러문항 설정하는 api
    @PutMapping("/admin/test/killer")
    public void setKiller(@RequestParam(value="BestkillerList[]") ArrayList<Integer> BestKillerList,
                          @RequestParam(value="killerList[]") ArrayList<Integer> KillerList,
                          @RequestParam(value="testcode") String testcode) {

        Test test = testRepository.findByTestCode(testcode).get();
        testService.setkiller(test,BestKillerList,KillerList);

    }

    // 수업별로 테스트 불러오는 api
    @GetMapping("/test/courses")
    public List<Test> getTests(@RequestParam Integer id){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Long idx = Long.valueOf(id);
        return testService.getTests(idx);
    }

    // 내 모든 테스트 불러오기
    @GetMapping("/test/all/my")
    public List<Test> getAllMyTests(){
        Student student = studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return testService.getAllMyTests(student);
    }





}
