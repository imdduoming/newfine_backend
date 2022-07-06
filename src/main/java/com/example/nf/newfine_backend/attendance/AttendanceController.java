package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.StudentRepostiory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "NewfinebackendDocker.ap-northeast-2.elasticbeanstalk.com")
public class AttendanceController {
    private static AttendanceService attendanceService;
    private static StudentRepostiory studentRepostiory;

    @RequestMapping (value = "/add/attendance" ,method = RequestMethod.POST)
    public Attendance addAttendance(@RequestBody AttendanceDto attendanceDto) {
    return attendanceService.addAttendance(attendanceDto);

    }
    @GetMapping("/get/all/attendances")
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }


}
