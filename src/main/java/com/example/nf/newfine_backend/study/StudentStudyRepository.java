package com.example.nf.newfine_backend.study;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.member.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentStudyRepository extends JpaRepository <StudentStudy,Long> {
    Optional<StudentStudy> findByStudentAndStudy(Student student, Study study);
}

