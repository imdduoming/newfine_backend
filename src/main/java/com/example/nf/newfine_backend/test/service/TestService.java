package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    public Test createTest(Long course_id, LocalDateTime testDate, String testName){
        Course course=courseRepository.findById(course_id).get();

//        List<Listener> listeners = courseService.getListeners(course_id);
//        List <StudentAttendance> studentAttendances = new ArrayList<>();
//        System.out.println("수강생");
//        System.out.println( listeners);
        Test test= new Test(course, testDate, testName);
        testRepository.save(test);

        return test;
    }
}
