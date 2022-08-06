package com.example.nf.newfine_backend.test.controller;

import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.TestDto;
import com.example.nf.newfine_backend.test.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @ResponseBody
    @RequestMapping(value = "/test/create", method = RequestMethod.POST)
    public Test createTest(HttpServletRequest request, TestDto testDto) {
        System.out.println(request);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+request.getParameter("test_date"));
        System.out.println(request.getParameter("course_id")+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        testDto.setCourse_id(Long.valueOf(request.getParameter("course_id")));
        testDto.setTestDate(LocalDate.parse(request.getParameter("test_date")));
        testDto.setTestName(request.getParameter("test_name"));
        testDto.setCourse_id(Long.valueOf(request.getParameter("course_id")));
        return testService.createTest(testDto);
    }

}
