package com.example.nf.newfine_backend.attendance.repository;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance,Long> {
}

