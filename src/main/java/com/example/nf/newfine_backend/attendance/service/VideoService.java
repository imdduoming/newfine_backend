package com.example.nf.newfine_backend.attendance.service;

import com.example.nf.newfine_backend.FCM.FCMService;
import com.example.nf.newfine_backend.FCM.RequestDTO;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.VideoReturnDto;
import com.example.nf.newfine_backend.attendance.repository.AttendanceRepository;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.branch.domain.BranchStudent;
import com.example.nf.newfine_backend.branch.repository.BranchStudentRepository;
import com.example.nf.newfine_backend.course.*;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    private final FCMService fcmService;

    public List<Attendance> getNowAttendance(Student student) {
        // 자신이 수강하고 있는 강의 찾기
        List<Listener> listenerList=listenerRepository.findListenersByStudent(student);
        List<Attendance> nowAttendances=new ArrayList<>();
        for(Listener listener : listenerList){
            // 리스너로 강의찾기
            List<Attendance> attendances= attendanceRepository.findAttendancesByCourseOrderByCreatedDateDesc(listener.getCourse());
            // 강의마다 조건에 맞는 출석 넣기
            for (Attendance attendance : attendances){

                StudentAttendance studentAttendance = studentattendanceRepository.findByStudentAndAttendance(student,attendance).get();
                if(studentAttendance.isAttend()==false && studentAttendance.isIsvideo()==false ){
                    // 출석하지 않았고 비디오 신청도 하지 않았다면

                        // 출석하려는 날짜가 오늘 날짜와 같고
//                        LocalTime endtime = LocalTime.parse(listener.getCourse().getEnd_time(), DateTimeFormatter.ofPattern("HH:mm"));
//                        LocalTime now= LocalTime.now();
//                        System.out.println("끝나는시간"+endtime);
//                        System.out.println("현재"+now);
//                        // 아직 현재가 끝나는 시간보다 전이라면
                        nowAttendances.add(attendance);


                }

            }
        }
        return nowAttendances;
    }

    @Transactional
    public StudentAttendance applyVideo(Long id , Student student) throws IOException {
        Attendance attendance = attendanceRepository.findById(id).get();
        StudentAttendance studentAttendance= studentattendanceRepository.findByStudentAndAttendance(student,attendance).get();
        Listener listener = listenerRepository.findById(student.getId()).orElseThrow(PhoneNumberNotFoundException::new);
        Course course = listener.getCourse();
        Teacher teacher = course.getTeacher();
        if(attendance.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
            // 출석하려는 날짜가 오늘 날짜와 같고
            LocalTime endtime = LocalTime.parse(attendance.getCourse().getEnd_time(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime now= LocalTime.now();
            System.out.println("끝나는시간"+endtime);
            System.out.println("현재"+now);
            if(now.isBefore(endtime))
            { // 아직 현재가 끝나는 시간보다 전이라면 동영상 신청 가능하고 출석처리
                studentAttendance.setAttend(true); // 출석처리
                studentAttendance.setIslate(false);
                studentAttendance.setIsvideo(true);
                studentattendanceRepository.save(studentAttendance);

                if (teacher.getDeviceToken() != null) {
                    RequestDTO requestDTO = new RequestDTO();
                    requestDTO.setTargetToken(student.getDeviceToken());
                    requestDTO.setTitle(course.getCName() + " 동영상 신청");
                    requestDTO.setBody(student.getName() + "학생이 " + course.getCName() + " 과목에 대한 동영상을 신청했습니다.");

                    System.out.println(requestDTO.getTargetToken() + " "
                            + requestDTO.getTitle() + " " + requestDTO.getBody());

                    fcmService.sendMessageTo(
                            requestDTO.getTargetToken(),
                            requestDTO.getTitle(),
                            requestDTO.getBody());
                }
            }
            else{
                studentAttendance.setAttend(false); // 결석 처리
                studentAttendance.setIslate(false);
                studentAttendance.setIsvideo(true);
                studentattendanceRepository.save(studentAttendance);
            }
        }
        else{
            studentAttendance.setAttend(false); // 출석처리
            studentAttendance.setIslate(false);
            studentAttendance.setIsvideo(true);
            studentattendanceRepository.save(studentAttendance);

        }
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
            List<Attendance> attendances = attendanceRepository.findAttendancesByCourseOrderByCreatedDateDesc(course);
            for (Attendance attendance : attendances){
                List<StudentAttendance> studentAttendances = studentattendanceRepository.findStudentAttendancesByAttendance(attendance);
                for(StudentAttendance studentAttendance : studentAttendances){
                    if (studentAttendance.isIsvideo()){
                        if (!studentAttendance.isReceiveVideo()){
                            // 비디오 신청 했고 비디오신청 못받았으면
                            VideoReturnDto videoReturnDto = new VideoReturnDto(course,studentAttendance,attendance,studentAttendance.getStudent());
                            newList.add(videoReturnDto);
                        }
                    }
                }
            }
        }
        return newList;
    }

    @Transactional
    public StudentAttendance editVideo(Long id) throws IOException {
        Student student = studentRepository.findById(id).get();
        Listener listener = listenerRepository.findById(student.getId()).orElseThrow(PhoneNumberNotFoundException::new);
        Course course = listener.getCourse();
        StudentAttendance studentAttendance=studentattendanceRepository.findById(id).get();
        studentAttendance.setIsvideo(true);
        studentAttendance.setReceiveVideo(true);
        studentattendanceRepository.save(studentAttendance);

        if (student.getDeviceToken() != null) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setTargetToken(student.getDeviceToken());
            requestDTO.setTitle(course.getCName() + " 동영상 신청 승인됨");
            requestDTO.setBody(course.getCName() + " 과목에 대한 동영상 신청이 승인되었습니다.");

            System.out.println(requestDTO.getTargetToken() + " "
                    + requestDTO.getTitle() + " " + requestDTO.getBody());

            fcmService.sendMessageTo(
                    requestDTO.getTargetToken(),
                    requestDTO.getTitle(),
                    requestDTO.getBody());
        }
        return studentAttendance;
    }
}
