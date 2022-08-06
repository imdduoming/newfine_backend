package com.example.nf.newfine_backend.test.controller;

import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.TestDto;
import com.example.nf.newfine_backend.test.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @ResponseBody
    @RequestMapping(value = "/makeTestForm.do", method = RequestMethod.POST)
    public Test createTest(HttpServletRequest request, TestDto testDto) throws Exception{
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+request.getParameter("test_date"));
        System.out.println(request.getParameter("test_name"));
        System.out.println(request.getParameter("course_id")+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        testDto.setTestDate(LocalDate.parse(request.getParameter("test_date")));
        testDto.setTestName(request.getParameter("test_name"));
        testDto.setCourse_id(Long.valueOf(request.getParameter("course_id")));
        return testService.createTest(testDto);
    }

}
