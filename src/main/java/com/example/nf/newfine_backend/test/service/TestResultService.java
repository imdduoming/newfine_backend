package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.*;
import com.example.nf.newfine_backend.test.dto.KillerDto;
import com.example.nf.newfine_backend.test.dto.MyAllTestDto;
import com.example.nf.newfine_backend.test.dto.NotCorrectDto;

import com.example.nf.newfine_backend.test.dto.student.TestResultDto;
import com.example.nf.newfine_backend.test.dto.student.TypeResultDto;
import com.example.nf.newfine_backend.test.repository.CourseTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.StudentTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestResultService {
    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    private final ListenerRepository listenerRepository;
    private final CourseTestResultsRepository courseTestResultsRepository;
    private final StudentTestResultsRepository studentTestResultsRepository;

    private final QuestionService questionService;

    public int getRank(Test test , int myScore){
        List<StudentTestResults> highStudents = studentTestResultsRepository.findAllByTestAndTotalScoreAfter(test,myScore);// 나보다 점수 높은 애들 가져오기
        return highStudents.size()+1;
    }

    public List<MyAllTestDto> getAllMyTests(Student student, Long test_id){
        String student_code= student.getTest_code();
        List<MyAllTestDto> myAllTestDtos = new ArrayList<>();
        // 테스트 아이디로 테스트 찾고 학생 수험번호 찾기
        Test test = testRepository.findById(test_id).get();
        Course course = test.getCourse();
        List<Test> allTests = testRepository.findTestsByCourse(course);
        int num=1;
        for (Test test1 : allTests){
            // 강의에 대한 모든 테스트 순회하고 테스트에 대한 학생의 답안지를 찾는다
            MyAllTestDto myAllTestDto = new MyAllTestDto();
            StudentTestResults studentTestResults=studentTestResultsRepository.findByTestAndStudentCode(test,student_code).get();
            myAllTestDto.setTest_num(num);
            myAllTestDto.setScore(studentTestResults.getTotalScore());
            int rank = getRank(test,studentTestResults.getTotalScore() ); // 순위 구하기
            myAllTestDto.setRank(rank);
            myAllTestDtos.add(myAllTestDto);
            num+=1;
        }

        return myAllTestDtos;

    }

    public TypeResultDto getTypeResults(Student student, Long test_id){
        TypeResultDto typeResultDto = new TypeResultDto();
        // 테스트 아이디로 테스트 찾고 학생 수험번호 찾기
        Test test = testRepository.findById(test_id).get();
        String student_code= student.getTest_code();

        // 킬러문항 / 준킬러문항 담기
        List<CourseTestResults> Bkiller = courseTestResultsRepository.findAllByType("bk"); // best killer
        List<CourseTestResults> Killer = courseTestResultsRepository.findAllByType("k"); // killer

        StudentTestResults studentTestResults=studentTestResultsRepository.findByTestAndStudentCode(test,student_code).get();

        // 킬러 , 준킬러 문항에 대한 나의 정오 및 분석
        List<KillerDto> BKillers = questionService.makeKiller(Bkiller,studentTestResults);
        List<KillerDto> Killers = questionService.makeKiller(Killer,studentTestResults);

        typeResultDto.setBkillerDtos(BKillers);
        typeResultDto.setKillerDtos(Killers);

        return typeResultDto;

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
            String student_ans= questionService.getStudentAns(studentTestResults,n); // 문제에 대한 학생의 답
            Double NotCorrectRate = 100 - courseTestResults.getCorrectAnsRate(); // 오답률
            Boolean isCorrect;
            if (student_ans.equals("O")){
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
//        List<StudentTestResults> highStudents = studentTestResultsRepository.findAllByTestAndTotalScoreAfter(test,myScore);// 나보다 점수 높은 애들 가져오기
//        int rank = highStudents.size()+1;// 내 순위
        int rank = getRank(test, myScore);
        List<StudentTestResults> allStudents = studentTestResultsRepository.findAllByTest(test);
        int students_num = allStudents.size(); // 총 학생 명수
        Double avg = get_avg(students_num,allStudents); // 평균구하기

        // 내 순위 , 점수 , 오답률 best 5
        testResultDto.setMyScore(myScore); // 학생점수
        testResultDto.setNotCorrectDtos(problems); // 오답문제에 대한 정보
        testResultDto.setRank(rank); // 학생 순위
        testResultDto.setTotal(students_num); //  총 학생 수
        testResultDto.setAvg(avg);

        return testResultDto;

    }

    public Double get_avg(int total_num, List<StudentTestResults> allStudents){
        int total_score=0;
        Double avg ;
        for (StudentTestResults studentTestResults1 : allStudents){
            total_score+=studentTestResults1.getTotalScore();
        }
        avg = Double.valueOf(total_score) / Double.valueOf(total_num);
        double new_avg = (double)Math.round(avg*100)/100;

        return new_avg;

    }



}
