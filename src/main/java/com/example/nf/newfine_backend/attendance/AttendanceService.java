package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.StudentRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepostiory studentRepository;

    public Attendance addAttendance(AttendanceDto attendanceDto) {
//        Student student= studentRepository.findBySphoneNumber(attendanceDto.getPhoneNumber());

        Attendance attendance= new Attendance(attendanceDto);
        attendanceRepository.save(attendance);
        return attendance;
    }
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }



}
