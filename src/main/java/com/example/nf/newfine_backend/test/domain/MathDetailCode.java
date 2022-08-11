package com.example.nf.newfine_backend.test.domain;

public enum MathDetailCode {
    미적분("01"), 기하("02"), 확통("03"), 수1("04"), 수2("05");

    private String mathDetailCode;

    MathDetailCode(String mathDetailCode){
        this.mathDetailCode=mathDetailCode;
    }

    public static String generateMathSubjectCode(SubjectCode subjectCode, String detailSubject){
        switch (detailSubject){
            case "미적분":
                return subjectCode.subjectCode()+미적분.mathDetailCode;
            case "기하":
                return subjectCode.subjectCode()+기하.mathDetailCode;
            case "확통":
                return subjectCode.subjectCode()+확통.mathDetailCode;
            case "수1":
                return subjectCode.subjectCode()+수1.mathDetailCode;
            default:
                return subjectCode.subjectCode()+수2.mathDetailCode;
        }
    }
}
