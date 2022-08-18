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

    public List<KillerDto> makeKiller(List<CourseTestResults> BKiller, StudentTestResults studentTestResults){
        List<KillerDto> killerDtos = new ArrayList<>();
        for (CourseTestResults courseTestResults : BKiller){
            KillerDto killerDto = new KillerDto();
            String ans = courseTestResults.getCorrectAns(); // 문제 답
            String n = courseTestResults.getQuestionNum(); // 문제 번호
            String student_ans= getStudentAns(studentTestResults,n); // 문제에 대한 학생의 답
            Double NotCorrectRate = 100 - courseTestResults.getCorrectAnsRate(); // 오답률
            Boolean isCorrect;
            if (student_ans.equals("O")){
                // 학생이 답을 맞췄다면
                isCorrect = true;
            }
            else{
                isCorrect = false;
            }

            ArrayList<String> mostChosen = mostChosen(courseTestResults,3);

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

    public String getStudentAns(StudentTestResults studentTestResults, String sn){
        int n=Integer.parseInt(sn);
        if (n==1){
            return studentTestResults.getQ1();
        } else if (n==2) {
            return studentTestResults.getQ2();
        }else if (n==3) {
            return studentTestResults.getQ3();
        }else if (n==4) {
            return studentTestResults.getQ4();
        }else if (n==5) {
            return studentTestResults.getQ5();
        }else if (n==6) {
            return studentTestResults.getQ6();
        }else if (n==7) {
            return studentTestResults.getQ7();
        }else if (n==8) {
            return studentTestResults.getQ8();
        }else if (n==9) {
            return studentTestResults.getQ9();
        }else if (n==10) {
            return studentTestResults.getQ10();
        }else if (n==11) {
            return studentTestResults.getQ11();
        }else if (n==12) {
            return studentTestResults.getQ12();
        }else if (n==13) {
            return studentTestResults.getQ13();
        }else if (n==14) {
            return studentTestResults.getQ14();
        }else if (n==15) {
            return studentTestResults.getQ15();
        }
        else if (n==15) {
            return studentTestResults.getQ15();
        }
        else if (n==15) {
            return studentTestResults.getQ15();
        }
        else if (n==15) {
            return studentTestResults.getQ15();
        }else if (n==16) {
            return studentTestResults.getQ16();
        }else if (n==17) {
            return studentTestResults.getQ17();
        }else if (n==18) {
            return studentTestResults.getQ18();
        }else if (n==19) {
            return studentTestResults.getQ19();
        }else if (n==20) {
            return studentTestResults.getQ20();
        }else if (n==21) {
            return studentTestResults.getQ21();
        }else if (n==22) {
            return studentTestResults.getQ22();
        }else if (n==23) {
            return studentTestResults.getQ23();
        }else if (n==24) {
            return studentTestResults.getQ24();
        }else if (n==25) {
            return studentTestResults.getQ25();
        }else if (n==26) {
            return studentTestResults.getQ26();
        }else if (n==27) {
            return studentTestResults.getQ27();
        }else if (n==28) {
            return studentTestResults.getQ28();
        }else if (n==29) {
            return studentTestResults.getQ29();
        }else if (n==30) {
            return studentTestResults.getQ30();
        }else if (n==31) {
            return studentTestResults.getQ31();
        }else if (n==32) {
            return studentTestResults.getQ32();
        }else if (n==33) {
            return studentTestResults.getQ33();
        }else if (n==34) {
            return studentTestResults.getQ34();
        }else if (n==35) {
            return studentTestResults.getQ35();
        }else if (n==36) {
            return studentTestResults.getQ36();
        }else if (n==37) {
            return studentTestResults.getQ37();
        }else if (n==38) {
            return studentTestResults.getQ38();
        }else if (n==39) {
            return studentTestResults.getQ39();
        }else {
            return studentTestResults.getQ40();
        }


    }



}
