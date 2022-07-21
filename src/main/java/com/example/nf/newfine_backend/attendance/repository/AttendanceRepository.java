package com.example.nf.newfine_backend.attendance.repository;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository <Attendance,Long> {

    List<Attendance> findAttendancesByCourse(Course course);
}

