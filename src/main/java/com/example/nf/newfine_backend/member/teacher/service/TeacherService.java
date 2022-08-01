package com.example.nf.newfine_backend.member.teacher.service;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.attendance.repository.StudentAttendanceRepository;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.dto.StudentResponseDto;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.member.teacher.dto.TeacherResponseDto;
import com.example.nf.newfine_backend.member.teacher.repository.TeacherRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.UNAUTHORIZED_MEMBER;

@RequiredArgsConstructor
@Service
@Slf4j
public class TeacherService {
    private final CourseRepository courseRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ListenerRepository listenerRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getTeacherCourses(Teacher teacher){
        List<Course> courseList=courseRepository.findCoursesByTeacher(teacher);
        return courseList;
    }

    @Transactional
    public StudentAttendance editAttendance(Long id, String state){
        System.out.println(state);
        StudentAttendance studentAttendance=studentAttendanceRepository.findById(id).get();
        if (state.equals("지각")){
            System.out.println(state);
            studentAttendance.setAttend(true);
            studentAttendance.setIslate(true);
            studentAttendanceRepository.save(studentAttendance);
        }
        else if(state.equals("결석")){
            System.out.println(state);
            studentAttendance.setAttend(false);
            studentAttendance.setIslate(false);
            studentAttendanceRepository.save(studentAttendance);
        }
        else
        {
            System.out.println(state);
            studentAttendance.setAttend(true);
            studentAttendance.setIslate(false);
            studentAttendanceRepository.save(studentAttendance);
        }

        return studentAttendance;
    }

    @Transactional(readOnly = true)
    public TeacherResponseDto getTeacherInfo() {
        // api 요청이 들어오면 필터에서 access token 복호화하여 유저 정보를 꺼낸 뒤  Security Context에 저장
        return teacherRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(TeacherResponseDto::of)
                .orElseThrow(() -> new CustomException(UNAUTHORIZED_MEMBER));
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


}
