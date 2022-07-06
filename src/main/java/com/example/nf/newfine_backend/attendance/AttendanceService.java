package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.StudentRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepostiory studentRepository;

    public Attendance addAttendance(AttendanceDto attendanceDto) {
        Student student= studentRepository.findBySphoneNumber(attendanceDto.getPhoneNumber());
        Attendance attendance= new Attendance(student);
        return attendance;
    }
}
