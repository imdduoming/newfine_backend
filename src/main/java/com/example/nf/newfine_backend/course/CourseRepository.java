package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findCoursesByTeacher(Teacher teacher);
    List<Course> findCoursesBySubjectType(String type);
}
