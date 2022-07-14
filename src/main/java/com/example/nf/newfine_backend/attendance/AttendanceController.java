package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import lombok.RequiredArgsConstructor;
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

    @PostMapping  (value = "/add/attendance/{attendance_id}" )
    public void addAttendance(@PathVariable Long attendance_id) {
        attendanceService.addAttendance(attendance_id);
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
