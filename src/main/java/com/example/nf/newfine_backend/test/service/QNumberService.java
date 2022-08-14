package com.example.nf.newfine_backend.test.service;

import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QNumberService {
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
