package com.example.nf.newfine_backend.test.service;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.test.domain.*;
import com.example.nf.newfine_backend.test.dto.TeacherTestResultDto;

import com.example.nf.newfine_backend.test.dto.teacher.TeacherKillerDto;
import com.example.nf.newfine_backend.test.dto.teacher.TeacherNotCorrectDto;
import com.example.nf.newfine_backend.test.dto.teacher.TeacherTypeResultDto;
import com.example.nf.newfine_backend.test.dto.teacher.TestRankDto;
import com.example.nf.newfine_backend.test.repository.CourseTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.StudentTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherTestService {
    private final TestRepository testRepository;
    private final CourseTestResultsRepository courseTestResultsRepository;
    private final StudentTestResultsRepository studentTestResultsRepository;
    private final QuestionService questionService;
    private final TestResultService testResultService;

    public List<TestRankDto> getTestRank (Teacher teacher, Long test_id){
        List<TestRankDto> testRankDtos = new ArrayList<>();
        Test test = testRepository.findById(test_id).get();
        List<StudentTestResults> allstudents = studentTestResultsRepository.findAllByTestOrderByTotalScoreDesc(test);
        int rank =0;
        int count = 1;
        int last_score =0;
        for (StudentTestResults studentTestResults : allstudents){
            TestRankDto testRankDto = new TestRankDto();
            String name = studentTestResults.getName();
            if (last_score == studentTestResults.getTotalScore()){
                // 지난 점수와 같으면
                count+=1;
            }
            else{
                last_score = studentTestResults.getTotalScore();
                rank+=count;
                count=1;
            }
            System.out.println("이름" + studentTestResults.getName());
            System.out.println("점수" + studentTestResults.getTotalScore());
            System.out.println("현재순위" + rank);
            System.out.println("count" + count);
            testRankDto.setRank(rank);
            testRankDto.setName(name);
            testRankDto.setScore(studentTestResults.getTotalScore());
            testRankDtos.add(testRankDto);
            rank+=1;
        }

        return testRankDtos;
    }

    public TeacherTypeResultDto getTeacherTypeResults(Teacher teacher, Long test_id){
        TeacherTypeResultDto teacherTypeResultDto = new TeacherTypeResultDto();
        // 테스트 아이디로 테스트 찾고 학생 수험번호 찾기
        Test test = testRepository.findById(test_id).get();

        // 킬러문항 / 준킬러문항 담기
        List<CourseTestResults> Bkiller = courseTestResultsRepository.findAllByTestAndType(test, "bk"); // best killer
        List<CourseTestResults> Killer = courseTestResultsRepository.findAllByTestAndType(test,"k"); // killer


        // 킬러 , 준킬러 문항 분석
        List<TeacherKillerDto> BKillers = questionService.makeTeacherKiller(Bkiller);
        List<TeacherKillerDto> Killers = questionService.makeTeacherKiller(Killer);

        teacherTypeResultDto.setBkillerDtos(BKillers);
        teacherTypeResultDto.setKillerDtos(Killers);

        return teacherTypeResultDto;

    }

    //선생님이 시험 평균 , 오답률 best 5
    public TeacherTestResultDto getTeacherTestResults(Teacher teacher, Long test_id){
        TeacherTestResultDto testResultDto = new TeacherTestResultDto();
        // 테스트 아이디로 테스트 찾고 학생 수험번호 찾기
        Test test = testRepository.findById(test_id).get();
        // 오답률 best 5
        List<CourseTestResults> courseTestResultsList= courseTestResultsRepository.findTop5ByTestOrderByCorrectAnsRate(test);

        // 오답문제에 대한 나의 결과 정보담기
        List<TeacherNotCorrectDto> problems = new ArrayList<>();
        int q_rank =1;
        for (CourseTestResults courseTestResults : courseTestResultsList){
            TeacherNotCorrectDto teacherNotCorrectDto = new TeacherNotCorrectDto();
            String ans = courseTestResults.getCorrectAns(); // 문제 답
            String n = courseTestResults.getQuestionNum(); // 문제 번호
            Double NotCorrectRate = 100 - courseTestResults.getCorrectAnsRate(); // 오답률

            teacherNotCorrectDto.setQ_num(n);
            teacherNotCorrectDto.setRate(NotCorrectRate);
            teacherNotCorrectDto.setQ_rank(q_rank);
            teacherNotCorrectDto.setRight_ans(ans);

            problems.add(teacherNotCorrectDto);

            q_rank+=1;

        }

        List<StudentTestResults> allStudents = studentTestResultsRepository.findAllByTest(test);
        int students_num = allStudents.size(); // 총 학생 명수
        Double avg = testResultService.get_avg(students_num,allStudents); // 평균구하기
        int highest = highestScore(test);
        int lowest  = lowestScore(test);

        // 평균점수 , 오답률 best 5
        testResultDto.setAvg(avg); // 평균
        testResultDto.setNotCorrectDtos(problems); // 오답문제에 대한 정보
        testResultDto.setHighest(highest);
        testResultDto.setLowest(lowest);

        return testResultDto;

    }

    public int highestScore (Test test){
      StudentTestResults studentTestResults = studentTestResultsRepository.findFirstByTestOrderByTotalScoreDesc(test).get();
      return studentTestResults.getTotalScore();

    }

    public int lowestScore (Test test){
        StudentTestResults studentTestResults = studentTestResultsRepository.findFirstByTestOrderByTotalScoreAsc(test).get();
        return studentTestResults.getTotalScore();

    }








}