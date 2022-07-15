package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
