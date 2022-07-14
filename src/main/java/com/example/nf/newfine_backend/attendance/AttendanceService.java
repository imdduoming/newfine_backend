package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.course.Course;
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
    private final StudentAttendanceRepository studentattendanceRepository;

    public Attendance makeAttendance(Course course) {
//        Student student= studentRepository.findBySphoneNumber(attendanceDto.getPhoneNumber());
        Attendance attendance= new Attendance(course);
        attendanceRepository.save(attendance);
        Long attendance_id=attendance.getAttendanceId();
        String a_id=Long.toString(attendance_id);
        Attendance attendance2=attendanceRepository.findById(attendance_id).get();
        String attendance_url="https://eb.newfine.tk/add/attendance/"+a_id;
        attendance2.setUrl(attendance_url);
        attendanceRepository.save(attendance2);
        return attendance2;
    }

    public void addAttendance(Long attedance_id) {
        System.out.println(attedance_id);
        Long student_id=Long.valueOf(1);
        Attendance attendance=attendanceRepository.findById(attedance_id).get();
        Student student= studentRepository.findById(student_id).get();
        StudentAttendance studentAttendance= new StudentAttendance(student,attendance);
        studentattendanceRepository.save(studentAttendance);
        System.out.println(studentAttendance);
    }


    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

//    public List<Attendance> getMyAttendances(String phone_number) {
//        return attendanceRepository.findByStudentPhone(phone_number);
//    }




}
