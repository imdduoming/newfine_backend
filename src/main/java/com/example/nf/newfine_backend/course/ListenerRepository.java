package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.member.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListenerRepository extends JpaRepository<Listener,Long> {

    List<Listener> findListenersByCourse(Course course);
    List<Listener> findListenersByStudent(Student student);

    Optional<Listener> findListenerByStudent(Student student);
}
