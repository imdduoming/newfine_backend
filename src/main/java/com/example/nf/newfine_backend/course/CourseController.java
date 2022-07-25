package com.example.nf.newfine_backend.course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {
    private final CourseService courseService;
    @GetMapping("/all/courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    // 수업의 학생들 불러오기
    @GetMapping("/listeners")
    public List<Listener> getListeners(@RequestParam Integer id){
        Long idx=Long.valueOf(id);
        return courseService.getListeners(idx);
    }

    // 학생이 수강하고 았는 정보 가져오기
    @GetMapping("/student/courses")
    public List<Listener> getStudentCourses(@RequestParam Integer id){
        Long idx=Long.valueOf(id);
        return courseService.getStudentCourses(idx);

    }



}
