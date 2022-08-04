package com.example.nf.newfine_backend.test.controller;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceDto;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.TestDto;
import com.example.nf.newfine_backend.test.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class testController {

    private final TestService testService;

//    @PostMapping(value = "/test/create")
//    public Test createTest(@RequestBody TestDto testDto) {
//        LocalDateTime testDate = testDto.getTestDate();
//        String testName = testDto.getTestName();
//        System.out.println(testDto.getTestName());
//        return testService.createTest(testDto.getCourse_id(), testDate, testName);
//
//    }

}
