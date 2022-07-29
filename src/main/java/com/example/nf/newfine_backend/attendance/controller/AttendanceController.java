package com.example.nf.newfine_backend.attendance.controller;

import com.amazonaws.services.transfer.model.UserDetails;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceDto;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.dto.StudentAttendanceDto;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import com.example.nf.newfine_backend.student.util.SecurityUtil;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    // 관리자가 수업시간 qr 코드 생성 api
    @PostMapping  (value = "/make/attendance" )
    public Attendance makeAttendance(@RequestBody AttendanceDto attendanceDto) {
        LocalDateTime startTime=attendanceDto.getStartTime();
        LocalDateTime endTime= attendanceDto.getEndTime();
        System.out.println(attendanceDto.getStartTime());
        Optional<Course> course = courseRepository.findById(attendanceDto.getCourse_id());
        Course course2 = course.get();
        System.out.println(course2);
        return attendanceService.makeAttendance(course2,attendanceDto.getStartTime(),attendanceDto.getEndTime());

}
    // 학생 출석 api
    @PostMapping  (value = "/add/attendance" )
    public int addAttendance(@RequestBody StudentAttendanceDto studentAttendanceDto) {
        Long attendance_id=Long.parseLong(studentAttendanceDto.getAttendance_id());
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        int ans=attendanceService.addAttendance(attendance_id,student);
        System.out.println(ans);
        return ans;
        // 출석하고 앱 화면으로 돌리기
    }

    @GetMapping("/attendances/all")
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }

    // 수업시간마다 매시간 출석부 가져오는 api , 출석 정보는 attendance 의 Student Attendance 로 가져오면 된다 .
    @GetMapping("/attendances")
    public List<Attendance> getAttendances(@RequestParam Integer id){
        Long idx=Long.valueOf(id);

        return attendanceService.getAttendances(idx);
    }

    // 매 수업시간 마다 출석 현황
    @GetMapping("/attendances/student")
    public List<StudentAttendance> getStudentAttendance(@RequestParam Integer id){
        Long idx=Long.valueOf(id);
        return attendanceService.getStudentAttendance(idx);
    }

//    @GetMapping("/get/attendance/{phone_number}")
//    public List<Attendance> getMyAttendances(@PathVariable String phone_number){
//
//        return attendanceService.getMyAttendances(phone_number);
//    }



}
