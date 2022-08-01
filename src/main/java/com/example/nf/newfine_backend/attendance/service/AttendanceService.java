package com.example.nf.newfine_backend.attendance.service;

import com.example.nf.newfine_backend.attendance.repository.AttendanceRepository;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.CourseService;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final CourseService courseService;

    public Attendance makeAttendance(Long course_id, LocalDateTime start, LocalDateTime end){
        Course course=courseRepository.findById(course_id).get();
        List <Listener> listeners = courseService.getListeners(course_id);
        List <StudentAttendance> studentAttendances = new ArrayList<>();
        System.out.println("수강생");
        System.out.println( listeners);
        Attendance attendance= new Attendance(course,start,end);
        attendanceRepository.save(attendance);
        for(Listener listener : listeners){
            System.out.println("수강생이름");
            System.out.println(listener.getStudent().getName());
            System.out.println("출석 id");
            System.out.println(attendance.getAttendanceId());
            StudentAttendance studentAttendance=new StudentAttendance(listener.getStudent(),attendance, null,false,false);
            studentattendanceRepository.save(studentAttendance);
            studentAttendances.add(studentAttendance);
        }
        System.out.println("학생 출석부");
        System.out.println(studentAttendances);
        Long attendance_id=attendance.getAttendanceId();
        String a_id=Long.toString(attendance_id);
        Attendance attendance2=attendanceRepository.findById(attendance_id).get();
        String attendance_url="https://eb.newfine.tk/attendance.html?idx="+a_id;
        attendance2.setUrl(attendance_url);
        attendance2.setStudentAttendances(studentAttendances);
        attendanceRepository.save(attendance2);
        return attendance2;
    }

    public int addAttendance(Long attedance_id,Student student) {
//        Student student= studentService.getUser();
        // 중복 출석 방지
        Attendance attendance=attendanceRepository.findById(attedance_id).get();
        LocalDateTime now_time = LocalDateTime.now();
        StudentAttendance studentAttendance= studentattendanceRepository.findByStudentAndAttendance(student,attendance).get();
        if (studentAttendance.isAttend()) {
            // 이미 같은 출석에 대해 같은 학생이 출석했다면
            return 0;
        }
        else {
            if (now_time.isAfter(attendance.getEndTime()))
            {
                // 지각 경우
                studentAttendance.setAttend(true);
                studentAttendance.setIslate(true);
                studentAttendance.setTime(now_time);
                studentattendanceRepository.save(studentAttendance);
            }

            else{
                // 지각하지 않고 출석
                studentAttendance.setAttend(true);
                studentAttendance.setIslate(false);
                studentAttendance.setTime(now_time);
                studentattendanceRepository.save(studentAttendance);
            }
            return 1;
        }
    }


    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendances(Long idx){
        Course course=courseRepository.findById(idx).get();
        List<Attendance> attendanceList=attendanceRepository.findAttendancesByCourse(course);
        return attendanceList;
    }

    public List<StudentAttendance> getStudentAttendance(Long idx){
        Attendance attendance=attendanceRepository.findById(idx).get();
        List<StudentAttendance> studentAttendances=studentattendanceRepository.findStudentAttendancesByAttendance(attendance);
        return studentAttendances;
    }
//    public List<StudentAttendance> getMyAttendance(Long idx) {
//        Course course=courseRepository.findById(idx).get(); // 강의 찾기
//        List<Attendance> attendances =attendanceRepository.findAttendancesByCourse(course);
//        System.out.println("내 출석정보");
//        System.out.println(attendances);
//        return studentattendanceRepository.findStudentAttendancesByAttendanceAndAndStudent();
//    }




}
