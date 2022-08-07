package com.example.nf.newfine_backend.attendance.service;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.VideoReturnDto;
import com.example.nf.newfine_backend.attendance.repository.AttendanceRepository;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.branch.domain.BranchStudent;
import com.example.nf.newfine_backend.branch.repository.BranchStudentRepository;
import com.example.nf.newfine_backend.course.*;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class VideoService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentattendanceRepository;
    private final StudentService studentService;
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final ListenerRepository listenerRepository;
    private final BranchStudentRepository branchStudentRepository;

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

                    System.out.println("학생 출석"+studentAttendance.isAttend());
                    if(attendance.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
                        // 출석하려는 날짜가 오늘 날짜와 같고
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

    // 부모님 번호 가져오는거
    public String getParentsNumber(Student student){
        BranchStudent branchStudent= branchStudentRepository.findByPhoneNumber(student.getPhoneNumber()).get();
        String phone = branchStudent.getParentPhoneNumber();
        return phone;
    }

    public List<VideoReturnDto> getVideos(Teacher teacher){
        List<Course> courses = courseRepository.findCoursesByTeacher(teacher);
        List<VideoReturnDto> newList = new ArrayList<>();
        for (Course course : courses){
            List<Attendance> attendances = attendanceRepository.findAttendancesByCourse(course);
            for (Attendance attendance : attendances){
                List<StudentAttendance> studentAttendances = studentattendanceRepository.findStudentAttendancesByAttendance(attendance);
                for(StudentAttendance studentAttendance : studentAttendances){
                    if (studentAttendance.isIsvideo()){
                        if (!studentAttendance.isReceiveVideo()){
                            // 비디오 신청 했고 비디오신청 못받았으면
                            VideoReturnDto videoReturnDto = new VideoReturnDto(course,studentAttendance);
                            newList.add(videoReturnDto);
                        }
                    }
                }
            }
        }
        return newList;
    }

    @Transactional
    public StudentAttendance editVideo(Long id){
        StudentAttendance studentAttendance=studentattendanceRepository.findById(id).get();
        studentAttendance.setIsvideo(true);
        studentAttendance.setReceiveVideo(true);
        studentattendanceRepository.save(studentAttendance);
        return studentAttendance;
    }
}