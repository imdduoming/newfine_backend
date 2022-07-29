package com.example.nf.newfine_backend.member.teacher.service;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.member.teacher.repository.TeacherRepository;
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
    private final TeacherRepository teacherRepository;
    private final ListenerRepository listenerRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getTeacherCourses(){
        Long teacher_id=Long.valueOf(1);
        Teacher teacher= teacherRepository.findById(teacher_id).get();
        List<Course> courseList=courseRepository.findCoursesByTeacher(teacher);
        return courseList;
    }

    @Transactional
    public StudentAttendance editAttendance(Long id, String state){
        System.out.println(state);
        StudentAttendance studentAttendance=studentAttendanceRepository.findById(id).get();
        if (state.equals("지각")){
            System.out.println(state);
            studentAttendance.setAttend(true);
            studentAttendance.setIslate(true);
            studentAttendanceRepository.save(studentAttendance);
        }
        else if(state.equals("결석")){
            System.out.println(state);
            studentAttendance.setAttend(false);
            studentAttendance.setIslate(false);
            studentAttendanceRepository.save(studentAttendance);
        }
        else
        {
            System.out.println(state);
            studentAttendance.setAttend(true);
            studentAttendance.setIslate(false);
            studentAttendanceRepository.save(studentAttendance);
        }

        return studentAttendance;
    }


}
