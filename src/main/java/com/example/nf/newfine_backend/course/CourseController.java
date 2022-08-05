package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {
    private final CourseService courseService;
    private final StudentRepository studentRepository;
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
    public List<Listener> getStudentCourses(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        List<Listener> listeners= courseService.getStudentCourses(student);
        for (Listener listener : listeners){
            System.out.println("수강생 이름");
            System.out.println(listener.getStudent().getName());


        }
        return listeners;
    }



}
