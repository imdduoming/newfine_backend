package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.example.nf.newfine_backend.test.dto.KillerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QNumberService qNumberService;
    private final ScoreService scoreService;
    private final QuestionService questionService;

    public List<KillerDto> makeKiller(List<CourseTestResults> BKiller, StudentTestResults studentTestResults){
        List<KillerDto> killerDtos = new ArrayList<>();
        for (CourseTestResults courseTestResults : BKiller){
            KillerDto killerDto = new KillerDto();
            String ans = courseTestResults.getCorrectAns(); // 문제 답
            String n = courseTestResults.getQuestionNum(); // 문제 번호
            String student_ans= qNumberService.getStudentAns(studentTestResults,n); // 문제에 대한 학생의 답
            Double NotCorrectRate = 100 - courseTestResults.getCorrectAnsRate(); // 오답률
            Boolean isCorrect;
            if (student_ans.equals("O")){
                // 학생이 답을 맞췄다면
                isCorrect = true;
            }
            else{
                isCorrect = false;
            }

            ArrayList<String> mostChosen = questionService.mostChosen(courseTestResults,3);

            killerDto.setIscorrect(isCorrect);
            killerDto.setRight_ans(ans);
            killerDto.setStudent_ans(student_ans);
            killerDto.setRate(NotCorrectRate);
            killerDto.setMost_chosen(mostChosen);
            killerDto.setQ_num(n);

            killerDtos.add(killerDto);

        }
        return killerDtos;


    }

    // 가장 많이 선택한 n개 답
    public ArrayList<String> mostChosen (CourseTestResults courseTestResults, int n) {

        Double[][] chList = new Double[5][5];
        chList[0][0] = 1.0;
        chList[0][1] =courseTestResults.getProportion1();
        chList[1][0] = 2.0;
        chList[1][1] =courseTestResults.getProportion2();
        chList[2][0] = 3.0;
        chList[2][1] =courseTestResults.getProportion3();

        chList[3][0] = 4.0;
        chList[3][1] =courseTestResults.getProportion4();

        chList[4][0] = 5.0;
        chList[4][1] =courseTestResults.getProportion5();

        System.out.println("chList" + chList);

        Arrays.sort(chList,(o1, o2)->
                Double.compare( o1[1],o2[1]));
        ArrayList<String> answer = new ArrayList<>();
        for(int i=0;i<n;i++){
            Double d_ans= chList[i][0];
            int ans = d_ans.intValue();
            String s_ans = Integer.toString(ans);
            answer.add(s_ans);
        }
        System.out.println(answer);
        return answer;

    }



}
