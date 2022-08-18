package com.example.nf.newfine_backend.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping ("/adminLogin")
    public String adminLogin(){
        return "index";
    }

    @RequestMapping ("/main")
    public String main(){
        return "main";
    }

    @RequestMapping ("/attendance")
    public String attendance(){
        return "attendance";
    }

    @RequestMapping ("/attendanceMake")
    public String attendanceMake(){
        return "attendanceMake";
    }

    @RequestMapping ("/studentInfo")
    public String studentInfo(){
        return "studentInfo";
    }

    @RequestMapping ("/study")
    public String study(){
        return "study";
    }

    @RequestMapping("/studyMake")
    public String studyMake(){
        return "studyMake";
    }

    @RequestMapping("/testUpload")
    public String testUpload(){
        return "testUpload";
    }
}
