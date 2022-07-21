package com.example.nf.newfine_backend.teacher;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepostiory extends JpaRepository<Teacher,Long> {
}
