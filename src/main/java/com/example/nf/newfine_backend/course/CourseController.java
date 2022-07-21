package com.example.nf.newfine_backend.course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/listeners/{idx}")
    public List<Listener> getListeners(@PathVariable Long idx){
        return courseService.getListners(idx);
    }

    // 학생이 수강하고 았는 정보 가져오기
    @GetMapping("/my/courses/{idx}")
    public List<Listener> getCourses(@PathVariable Long idx){
        return courseService.getMyCourses(idx);
    }


}
