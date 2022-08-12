package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.test.domain.MathDetailCode;
import com.example.nf.newfine_backend.test.domain.ScienceDetailCode;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
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
    private final ListenerRepository listenerRepository;
    public Test createTest(TestDto testDto){
        Course course=courseRepository.findById(testDto.getCourse_id()).get();

        String code = "";
        if(course.getSubject()=="과학"){
            code=ScienceDetailCode.generateScienceSubjectCode(course.getSubjectType());
        } else if(course.getSubject()=="수학"){
//            code= MathDetailCode.generateMathSubjectCode(SubjectCode.수학, course.getSubjectType());
            code= MathDetailCode.generateMathSubjectCode(course.getSubjectType());
            System.out.println("테스트:             "+code);
        }
        System.out.println("테스트:             "+code);
        System.out.println("SubjectCode:             "+SubjectCode.과학+", "+SubjectCode.과학.subjectCode());

//        List<Listener> listeners = courseService.getListeners(course_id);
//        List <StudentAttendance> studentAttendances = new ArrayList<>();
//        System.out.println("수강생");
//        System.out.println( listeners);
        Test test= new Test(course, testDto.getTestDate(), testDto.getTestName());

        String code2 = String.format("%04d", test.getId());
        code+=code2;

        System.out.println("테스트:             "+code);

        test.setTestCode(code);
        testRepository.save(test);

        return test;
    }
    public List<Test> getTests(Long course_id){
        Course course=courseRepository.findById(course_id).get();
        List<Test> tests=testRepository.findTestsByCourse(course);
        return tests;

    }

    public List<Test> getAllMyTests(Student student){
        List<Listener> listeners = listenerRepository.findListenersByStudent(student);
        List<Test> tests = new ArrayList<>();
        for(Listener listener : listeners) {
            for (Test test : listener.getCourse().getTests()) {
                tests.add(test);
            }
        }
        return tests;
    }
}
