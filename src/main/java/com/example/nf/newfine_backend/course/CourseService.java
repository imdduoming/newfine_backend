package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final StudentRepository studentRepository;
    private final ListenerRepository listenerRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Listener> getListners(Long id){
        Course course=courseRepository.findById(id).get();
        return listenerRepository.findListenersByCourse(course);
    }

    public List<Listener> getMyCourses(Long id){
        Student student=studentRepository.findById(id).get();
        List<Listener> listeners =listenerRepository.findListenersByStudent(student);
        return listeners;

    }
}
