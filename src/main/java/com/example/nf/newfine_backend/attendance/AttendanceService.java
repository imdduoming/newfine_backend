package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.StudentRepostiory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepostiory studentRepository;

    public Attendance addAttendance(AttendanceDto attendanceDto) {
//        Student student= studentRepository.findBySphoneNumber(attendanceDto.getPhoneNumber());
        System.out.println(attendanceDto.getStudentName());
        Attendance attendance= new Attendance(attendanceDto);
        attendanceRepository.save(attendance);
        System.out.println(attendance);
        return attendance;
    }
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }



}
