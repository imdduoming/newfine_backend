package com.example.nf.newfine_backend.test.domain;

public enum ScienceDetailCode {
    화학("01"), 물리("02"), 생명과학("03"), 지구과학("04"), 공통과학("05");

    private String scienceDetailCode;

    ScienceDetailCode(String scienceDetailCode){
        this.scienceDetailCode=scienceDetailCode;
    }

    public String scienceDetailCode(){
        return scienceDetailCode;
    }

    public static String generateScienceSubjectCode(String detailSubject){
        switch (detailSubject){
            case "화학":
                return "S"+화학.scienceDetailCode();
            case "물리":
                return "S"+물리.scienceDetailCode();
            case "생명과학":
                return "S"+생명과학.scienceDetailCode();
            case "지구과학":
                return "S"+지구과학.scienceDetailCode();
            default:
                return "S"+공통과학.scienceDetailCode();
        }
    }
}
