package com.example.nf.newfine_backend.attendance.controller;

import com.amazonaws.services.transfer.model.UserDetails;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.dto.AttendanceDto;
import com.example.nf.newfine_backend.attendance.service.AttendanceService;
import com.example.nf.newfine_backend.attendance.dto.StudentAttendanceDto;
import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final CourseRepository courseRepository;

    @PostMapping  (value = "/make/attendance" )
    public Attendance makeAttendance(@RequestBody AttendanceDto attendanceDto) {

        System.out.println(attendanceDto.getCourse_id());
        Optional<Course> course = courseRepository.findById(attendanceDto.getCourse_id());
        Course course2 = course.get();
        System.out.println(course2);
        return attendanceService.makeAttendance(course2);

}

    @PostMapping  (value = "/add/attendance" )
    public int addAttendance(@RequestBody StudentAttendanceDto studentAttendanceDto) {
        Long attendance_id=Long.parseLong(studentAttendanceDto.getAttendance_id());
        int ans=attendanceService.addAttendance(attendance_id);
        System.out.println(ans);
        return ans;
        // 출석하고 앱 화면으로 돌리기
    }

    @GetMapping("/get/all/attendances")
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }

//    @GetMapping("/get/attendance/{phone_number}")
//    public List<Attendance> getMyAttendances(@PathVariable String phone_number){
//
//        return attendanceService.getMyAttendances(phone_number);
//    }


}
