package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.test.domain.*;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.dto.*;
import com.example.nf.newfine_backend.test.repository.CourseTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.StudentTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    private final ListenerRepository listenerRepository;
    private final CourseTestResultsRepository courseTestResultsRepository;
    private final StudentTestResultsRepository studentTestResultsRepository;

    private final QuestionService questionService;

    public Test createTest(TestDto testDto){
        Course course=courseRepository.findById(testDto.getCourse_id()).get();

        String code = "";
//        System.out.println((course.getSubject()=="과학"));
//        System.out.println(Objects.equals(course.getSubject(), "과학"));
//        System.out.println("course.getSubject(): "+ course.getSubject());
        if(Objects.equals(course.getSubject(), "과학")){
//            System.out.println("enum 으로 하기"+ScienceDetailCode.generateScienceSubjectCode(course.getSubjectType()));
//            code+="S";
            code=ScienceDetailCode.generateScienceSubjectCode(course.getSubjectType());
//            String sc= ScienceDetailCode.valueOf(course.getSubjectType()).scienceDetailCode();
//            System.out.println("ScienceDetailCode:             "+sc);
//            code+=sc;
        } else if(Objects.equals(course.getSubject(), "수학")){
//            code= MathDetailCode.generateMathSubjectCode(SubjectCode.수학, course.getSubjectType());
            code= MathDetailCode.generateMathSubjectCode(course.getSubjectType());
//            code+="M";
//            MathDetailCode mc=MathDetailCode.valueOf(course.getSubjectType());
//            code+=mc;
        }
        System.out.println("테스트:             "+code);
//        System.out.println("SubjectCode:             "+SubjectCode.과학+", "+SubjectCode.과학.subjectCode());

        Test test= new Test(course, testDto.getTestDate(), testDto.getTestName());
//        System.out.println("테스트 객체: "+ test);
//        System.out.println("테스트 객체 아이디: "+ test.getId());
        testRepository.save(test);
//        System.out.println("테스트 객체 아이디: "+ test.getId());
        code+= String.format("%04d", test.getId());
//        code+=code2;

        System.out.println("최종 테스트 코드 :             "+code);

        test.setTestCode(code);
        testRepository.save(test);

        return test;
    }

    @Transactional
    public void setkiller(Test test , ArrayList<Integer> BestkillerList , ArrayList<Integer> killerList){
        System.out.println("킬러문항"+ BestkillerList);
        System.out.println("준킬러문항"+killerList);
        List<CourseTestResults> testResults = courseTestResultsRepository.findAllByTest(test);
        for (CourseTestResults courseTestResults : testResults){
            int q_num = Integer.parseInt(courseTestResults.getQuestionNum());
            System.out.println("문제번호"+q_num);
            for(int i : BestkillerList){
                System.out.println("i"+i);
                if (i==q_num){
                    // 킬러문항이라면
                    courseTestResults.setType("bk");
                    courseTestResultsRepository.save(courseTestResults);
                }

            }
            for (int i: killerList){
                System.out.println("i"+i);
                if (i==q_num){
                    // 준킬러문항이라면
                    courseTestResults.setType("k");
                    courseTestResultsRepository.save(courseTestResults);
                }
            }
        }

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
