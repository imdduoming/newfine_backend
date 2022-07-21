package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    //선생님이 자신 강의 불러오기
    @GetMapping("/teacher/courses")
    public List<Course> getTeacherCourses(){
        return teacherService.getTeacherCourses();
    }


}


