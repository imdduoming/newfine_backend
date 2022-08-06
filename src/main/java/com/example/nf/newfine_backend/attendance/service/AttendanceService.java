package com.example.nf.newfine_backend.attendance.service;

import com.example.nf.newfine_backend.attendance.repository.AttendanceRepository;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.*;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private final ListenerRepository listenerRepository;


    public Attendance makeAttendance(Long course_id, LocalDateTime start, LocalDateTime end){
        Course course=courseRepository.findById(course_id).get();
        List <Listener> listeners = courseService.getListeners(course_id);
        List <StudentAttendance> studentAttendances = new ArrayList<>();
        Attendance attendance= new Attendance(course,start,end);
        attendanceRepository.save(attendance);

        for(Listener listener : listeners){
            StudentAttendance studentAttendance=new StudentAttendance(listener.getStudent(),attendance, null,false,false,false);
            studentattendanceRepository.save(studentAttendance);
            studentAttendances.add(studentAttendance);
        }

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
    public List<StudentAttendance> getMyAttendance(Long idx,Student student) {
        Course course=courseRepository.findById(idx).get(); // 강의 찾기
        List<Attendance> attendances =attendanceRepository.findAttendancesByCourse(course);
        List<StudentAttendance> studentAttendances=new ArrayList<>();
        for(Attendance attendance : attendances){
            // 출석마다 학생 출석 정보 찾기
            StudentAttendance studentAttendance=studentattendanceRepository.findByStudentAndAttendance(student,attendance).get();
            studentAttendances.add(studentAttendance);

        }
        System.out.println(studentAttendances);
        return studentAttendances;
    }

    public List<Attendance> getNowAttendance(Student student) {
        // 자신이 수강하고 있는 강의 찾기
        List<Listener> listenerList=listenerRepository.findListenersByStudent(student);
        List<Attendance> nowAttendances=new ArrayList<>();
        for(Listener listener : listenerList){
            // 리스너로 강의찾기
            List<Attendance> attendances= attendanceRepository.findAttendancesByCourse(listener.getCourse());
            // 강의마다 조건에 맞는 출석 넣기
            for (Attendance attendance : attendances){
                // 시간이 오늘이고 수업끝나는시간이 현재시간보다 after
                System.out.println(attendance.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                // 현재와 날짜가 같아야함
                StudentAttendance studentAttendance = studentattendanceRepository.findByStudentAndAttendance(student,attendance).get();
                if(studentAttendance.isAttend()==false){
                    // 출석하지 않았고
                    if(attendance.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
                        LocalTime endtime = LocalTime.parse(listener.getCourse().getEnd_time(), DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime now= LocalTime.now();
                        System.out.println("끝나는시간"+endtime);
                        System.out.println("현재"+now);
                        if(now.isBefore(endtime))
                        { // 아직 현재가 끝나는 시간보다 전이라면
                            nowAttendances.add(attendance);
                        }
                    }
                }

            }
        }
        return nowAttendances;
    }

    @Transactional
    public StudentAttendance applyVideo(Long id , Student student){
        Attendance attendance = attendanceRepository.findById(id).get();
        StudentAttendance studentAttendance= studentattendanceRepository.findByStudentAndAttendance(student,attendance).get();
        studentAttendance.setAttend(true); // 출석처리
        studentAttendance.setIslate(false);
        studentAttendance.setIsvideo(true);
        studentattendanceRepository.save(studentAttendance);

        return studentAttendance;
    }

}
