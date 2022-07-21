package com.example.nf.newfine_backend.attendance.service;

import com.example.nf.newfine_backend.attendance.repository.AttendanceRepository;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import com.example.nf.newfine_backend.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentattendanceRepository;
    private final StudentService studentService;
    private final CourseRepository courseRepository;

    public Attendance makeAttendance(Course course) {
        Attendance attendance= new Attendance(course);
        attendanceRepository.save(attendance);
        Long attendance_id=attendance.getAttendanceId();
        String a_id=Long.toString(attendance_id);
        Attendance attendance2=attendanceRepository.findById(attendance_id).get();
        String attendance_url="https://eb.newfine.tk/attendance.html?idx="+a_id;
        attendance2.setUrl(attendance_url);
        attendanceRepository.save(attendance2);
        return attendance2;
    }

    public int addAttendance(Long attedance_id) {
//        Student student= studentService.getUser();
        // 중복 출석 방지

        System.out.println(attedance_id);
        Long student_id=Long.valueOf(1);
        Attendance attendance=attendanceRepository.findById(attedance_id).get();
        Student student= studentRepository.findById(student_id).get();
        if (studentattendanceRepository.findByStudentAndAttendance(student,attendance).isPresent()) {
            // 이미 같은 출석에 대해 같은 학생이 출석했다면
            return 0;
        }
        else {
            StudentAttendance studentAttendance = new StudentAttendance(student, attendance);
            studentattendanceRepository.save(studentAttendance);
            System.out.println(studentAttendance);
            return 1;
        }
    }


    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendance(Long idx){
        Course course=courseRepository.findById(idx).get();
        List<Attendance> attendanceList=attendanceRepository.findAttendancesByCourse(course);
        return attendanceList;
    }
//    public List<Attendance> getMyAttendances(String phone_number) {
//        return attendanceRepository.findByStudentPhone(phone_number);
//    }




}
