package com.example.nf.newfine_backend.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance,Long> {
    List<StudentAttendance> findByStudentPhone(String phone_number);
}

