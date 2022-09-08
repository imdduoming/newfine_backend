package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public List<Course> getTypeCourses(String type) {
        return courseRepository.findCoursesBySubjectType(type);
    }
    public List<Listener> getListeners(Long id){
        Course course=courseRepository.findById(id).get();
        List<Listener> Listeners=listenerRepository.findListenersByCourse(course);
        Collections.sort(Listeners, new ListComparator());
        System.out.println(Listeners);
       return Listeners;
//        return listenerRepository.findListenersByCourse(course);

    }

    public List<Listener> getStudentCourses(Student student){
        List<Listener> listeners =listenerRepository.findListenersByStudent(student);
        return listeners;
    }



}
