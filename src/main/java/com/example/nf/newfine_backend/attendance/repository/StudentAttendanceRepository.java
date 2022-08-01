package com.example.nf.newfine_backend.attendance.repository;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.member.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance,Long> {
    Optional<StudentAttendance> findByStudentAndAttendance(Student student,Attendance attendance);


    List<StudentAttendance> findStudentAttendancesByAttendanceAndAndStudent(Attendance attendance,Student student);

    List<StudentAttendance> findStudentAttendancesByAttendance(Attendance attendance);
}

