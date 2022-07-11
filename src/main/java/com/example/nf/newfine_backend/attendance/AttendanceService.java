package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Course;
import com.example.nf.newfine_backend.StudentRepostiory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepostiory studentRepository;
    private final StudentAttendanceRepository studentattendanceRepository;

    public Attendance makeAttendance(Course course) {
//        Student student= studentRepository.findBySphoneNumber(attendanceDto.getPhoneNumber());
        Attendance attendance= new Attendance(course);
        Long attendance_id=attendance.getAttendanceId();
        String a_id=Long.toString(attendance_id);
        System.out.println(a_id);
        String attendance_url="https://eb.newfine.tk/add/attendance/"+a_id;
        System.out.println(attendance_url);
        attendance.setUrl(attendance_url);
        attendanceRepository.save(attendance);
        System.out.println(attendance);
        return attendance;
    }

    public StudentAttendance addAttendance(StudentAttendanceDto studentAttendanceDto) {
//        Student student= studentRepository.findBySphoneNumber(attendanceDto.getPhoneNumber());
        System.out.println(studentAttendanceDto.getStudentPhoneNumber());
        StudentAttendance studentAttendance= new StudentAttendance(studentAttendanceDto);
        studentattendanceRepository.save(studentAttendance);
        System.out.println(studentAttendance);
        return studentAttendance;
    }


    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

//    public List<Attendance> getMyAttendances(String phone_number) {
//        return attendanceRepository.findByStudentPhone(phone_number);
//    }




}
