package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListenerRepository extends JpaRepository<Listener,Long> {

    List<Listener> findListenersByCourse(Course course);
    List<Listener> findListenersByStudent(Student student);
}
