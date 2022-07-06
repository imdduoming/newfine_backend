package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.Student;
import com.example.nf.newfine_backend.StudentRepostiory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AttendanceController {
    private static AttendanceService attendanceService;
    private static StudentRepostiory studentRepostiory;
    @CrossOrigin(origins = "*")
    @PostMapping("/add/attendance")
    public Attendance addAttendance(@RequestBody AttendanceDto attendanceDto) {
        // attendance
        // 출석 정보를 만들기 위해서 필요한 것

        // 저장하는 것은 Dto가 아니라 Attendance 이니, Attendance 에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        return attendanceService.addAttendance(attendanceDto);

    }
    @GetMapping("/get/all/attendances")
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }


}
