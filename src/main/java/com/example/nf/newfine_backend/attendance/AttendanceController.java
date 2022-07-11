package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Course;
import com.example.nf.newfine_backend.CourseRepository;
import com.example.nf.newfine_backend.StudentRepostiory;
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

    @PostMapping  (value = "/add/attendance" )
    public StudentAttendance addAttendance(@RequestBody StudentAttendanceDto studentAttendanceDto) {
        return attendanceService.addAttendance(studentAttendanceDto);

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
