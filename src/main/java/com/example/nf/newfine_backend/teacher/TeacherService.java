package com.example.nf.newfine_backend.teacher;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TeacherService {
    private final CourseRepository courseRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepostiory teacherRepostiory;
    private final ListenerRepository listenerRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getTeacherCourses(){
        Long teacher_id=Long.valueOf(1);
        Teacher teacher=teacherRepostiory.findById(teacher_id).get();
        List<Course> courseList=courseRepository.findCoursesByTeacher(teacher);
        return courseList;
    }

    @Transactional
    public StudentAttendance editAttendance(Long id, String state){
        System.out.println(state);
        StudentAttendance studentAttendance=studentAttendanceRepository.findById(id).get();
        if (state=="지각"){
            studentAttendance.setAttend(true);
            studentAttendance.setIslate(true);
        }
        else if(state=="결석"){
            studentAttendance.setAttend(false);
            studentAttendance.setIslate(false);
        }
        else{
            studentAttendance.setAttend(true);
            studentAttendance.setIslate(false);
        }
        studentAttendanceRepository.save(studentAttendance);
        System.out.println(studentAttendance);
        return studentAttendance;
    }


}
