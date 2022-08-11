package com.example.nf.newfine_backend.test.domain;

public enum MathDetailCode {
    미적분("01"), 기하("02"), 확통("03"), 수1("04"), 수2("05");

    private String mathDetailCode;

    MathDetailCode(String mathDetailCode){
        this.mathDetailCode=mathDetailCode;
    }

    public String mathDetailCode(){
        return mathDetailCode;
    }

    public static String generateMathSubjectCode(String detailSubject){
        switch (detailSubject){
            case "미적분":
                System.out.println(미적분.mathDetailCode());
                return "0"+미적분.mathDetailCode();
            case "기하":
                return "0"+기하.mathDetailCode();
            case "확통":
                return "0"+확통.mathDetailCode();
            case "수1":
                return "0"+수1.mathDetailCode();
            default:
                return "0"+수2.mathDetailCode();
        }
    }
}
