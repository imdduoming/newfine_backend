package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.attendance.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {
    private final CourseService courseService;
    @GetMapping("/get/all/courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}
