package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.test.domain.MathDetailCode;
import com.example.nf.newfine_backend.test.domain.ScienceDetailCode;
import com.example.nf.newfine_backend.test.domain.SubjectCode;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.TestDto;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    public Test createTest(TestDto testDto){
        Course course=courseRepository.findById(testDto.getCourse_id()).get();

        String code = "";
        if(course.getSubject()=="과학"){
            code=ScienceDetailCode.generateScienceSubjectCode(SubjectCode.과학, course.getSubjectType());
        } else if(course.getSubject()=="수학"){
            code= MathDetailCode.generateMathSubjectCode(SubjectCode.수학, course.getSubjectType());
        }

//        List<Listener> listeners = courseService.getListeners(course_id);
//        List <StudentAttendance> studentAttendances = new ArrayList<>();
//        System.out.println("수강생");
//        System.out.println( listeners);
        Test test= new Test(course, testDto.getTestDate(), testDto.getTestName());
        testRepository.save(test);

        code+=test.getId();

        test.setTestCode(code);

        return test;
    }
}
