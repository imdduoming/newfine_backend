package com.example.nf.newfine_backend.attendance.repository;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository <Attendance,Long> {

}

