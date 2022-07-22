package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findCoursesByTeacher(Teacher teacher);
}
