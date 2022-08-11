package com.example.nf.newfine_backend.test.domain;

public enum SubjectCode {
//    MATH("01"), CHEMISTRY("02"), PHYSICS("03"), BIOLOGY("04"), EARTH_SCIENCE("05"), SCIENCE("06");
    수학("0"), 과학("1");

    private String subjectCode;

    SubjectCode(String subjectCode){
        this.subjectCode=subjectCode;
    }

    public String subjectCode(){
        return subjectCode;
    }
}
