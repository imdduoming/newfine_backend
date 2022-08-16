package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.test.domain.*;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.dto.NotCorrectDto;
import com.example.nf.newfine_backend.test.dto.TestDto;
import com.example.nf.newfine_backend.test.dto.TestResultDto;
import com.example.nf.newfine_backend.test.repository.CourseTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.StudentTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.MathException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    private final ListenerRepository listenerRepository;
    private final CourseTestResultsRepository courseTestResultsRepository;
    private final StudentTestResultsRepository studentTestResultsRepository;
    private final QNumberService qNumberService;
    private final ScoreService scoreService;

    public Test createTest(TestDto testDto){
        Course course=courseRepository.findById(testDto.getCourse_id()).get();

        String code = "";
        System.out.println((course.getSubject()=="과학"));
        System.out.println(Objects.equals(course.getSubject(), "과학"));
        System.out.println("course.getSubject(): "+ course.getSubject());
        if(Objects.equals(course.getSubject(), "과학")){
//            code=ScienceDetailCode.generateScienceSubjectCode(course.getSubjectType());
            code+="S";
            ScienceDetailCode sc= ScienceDetailCode.valueOf(course.getSubjectType());
            System.out.println("ScienceDetailCode:             "+sc);
            code+=sc;
        } else if(Objects.equals(course.getSubject(), "수학")){
//            code= MathDetailCode.generateMathSubjectCode(SubjectCode.수학, course.getSubjectType());
//            code= MathDetailCode.generateMathSubjectCode(course.getSubjectType());
            code+="M";
            MathDetailCode mc=MathDetailCode.valueOf(course.getSubjectType());
            code+=mc;
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



    public TestResultDto getTestResults(Student student, Long test_id){
        TestResultDto testResultDto = new TestResultDto();
        // 테스트 아이디로 테스트 찾고 학생 수험번호 찾기
        Test test = testRepository.findById(test_id).get();
        String student_code= student.getTest_code();
        // 오답률 best 5
        List<CourseTestResults> courseTestResultsList= courseTestResultsRepository.findTop5ByTestOrderByCorrectAnsRate(test);
        // 학생 답안 가져오기
        StudentTestResults studentTestResults=studentTestResultsRepository.findByTestAndStudentCode(test,student_code).get();

        // 오답문제에 대한 나의 결과 정보담기
        List<NotCorrectDto> problems = new ArrayList<>();
        int q_rank =1;
        for (CourseTestResults courseTestResults : courseTestResultsList){
            NotCorrectDto notCorrectDto = new NotCorrectDto();
            String ans = courseTestResults.getCorrectAns(); // 문제 답
            String n = courseTestResults.getQuestionNum(); // 문제 번호
            String student_ans= qNumberService.getStudentAns(studentTestResults,n); // 문제에 대한 학생의 답
            Double NotCorrectRate = 100 - courseTestResults.getCorrectAnsRate(); // 오답률
            Boolean isCorrect;
            if (student_ans.equals(ans)){
                // 학생이 답을 맞췄다면
                isCorrect = true;
            }
            else{
                isCorrect = false;
            }

            notCorrectDto.setIsCorrect(isCorrect);
            notCorrectDto.setQ_num(n);
            notCorrectDto.setRate(NotCorrectRate);
            notCorrectDto.setStudent_ans(student_ans);
            notCorrectDto.setRight_ans(ans);
            notCorrectDto.setQ_rank(q_rank);
            problems.add(notCorrectDto);

            q_rank+=1;

        }

        int myScore = studentTestResults.getTotalScore();// 학생 점수
        List<StudentTestResults> highStudents = studentTestResultsRepository.findAllByTestAndTotalScoreAfter(test,myScore);// 나보다 점수 높은 애들 가져오기
        int rank = highStudents.size()+1;// 내 순위
        List<StudentTestResults> allStudents = studentTestResultsRepository.findAllByTest(test);
        int students_num = allStudents.size(); // 총 학생 명수
        Double avg = scoreService.get_avg(students_num,allStudents); // 평균구하기

        // 내 순위 , 점수 , 오답률 best 5
        testResultDto.setMyScore(myScore); // 학생점수
        testResultDto.setNotCorrectDtos(problems); // 오답문제에 대한 정보
        testResultDto.setRank(rank); // 학생 순위
        testResultDto.setTotal(students_num); //  총 학생 수
        testResultDto.setAvg(avg);

        return testResultDto;

    }



}
