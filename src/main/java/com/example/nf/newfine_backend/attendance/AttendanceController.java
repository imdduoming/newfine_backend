package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.StudentRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final StudentRepostiory studentRepostiory;

    @PostMapping  (value = "/add/attendance" )
    public Attendance addAttendance(@RequestBody AttendanceDto attendanceDto) {

        return attendanceService.addAttendance(attendanceDto);

    }
    @GetMapping("/get/all/attendances")
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }


}
