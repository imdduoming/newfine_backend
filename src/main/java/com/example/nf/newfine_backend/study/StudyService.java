package com.example.nf.newfine_backend.study;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.PointService;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudyService {

    private final StudentRepository studentRepository;

    private final StudentService studentService;
    private final StudyRepository studyRepository;
    private final StudentStudyRepository studentStudyRepository;
    private final PointService pointService;

    public Study makeStudy(LocalDateTime start){
        Study study= new Study(start);
        studyRepository.save(study);
        Long study_id=study.getStudy_id();
        String a_id=Long.toString(study_id);
        Study study1=studyRepository.findById(study_id).get();
        String study_url="https://eb.newfine.tk/study.html?idx="+a_id;
        study1.setUrl(study_url);
        studyRepository.save(study1);
        return study1;
    }

    // 자습 시작
    public int enterStudy(Long study_id,Student student) {
//        Student student= studentService.getUser();
        // 중복 출석 방지
        Study study=studyRepository.findById(study_id).get();
        LocalDateTime now_time = LocalDateTime.now();
        Boolean isIn;
        Boolean isOut;
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (studentStudyRepository.findByStudentAndStudy(student,study).isPresent()) {
            // 입장한 경우
            // 1. 큐알코드 또 찍은 경우
            return 0;
        }
        else {
                // 입장
                isIn=true;
                isOut=false;
                StudentStudy studentStudy = new StudentStudy(student, study,now_time,isIn,isOut);
                studentStudyRepository.save(studentStudy);
                return 1;
            }

    }

    // 자습 퇴실
    public int endStudy(Long study_id,Student student){
        Study study=studyRepository.findById(study_id).get();
        LocalDateTime now_time = LocalDateTime.now();
        Boolean isIn;
        Boolean isOut;
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (studentStudyRepository.findByStudentAndStudy(student,study).isPresent()) {
            // 입장한 경우 , 제대로된 퇴실
            StudentStudy studentStudy=studentStudyRepository.findByStudentAndStudy(student,study).get();
            studentStudy.setEndTime(now_time); // 퇴실시간
            long total = ChronoUnit.MINUTES.between(studentStudy.getStartTime(),now_time);
            studentStudy.setTotal(total);
            studentStudy.setOut(true); //나갔다고 표시
            studentStudyRepository.save(studentStudy);
            pointService.create(student,"자습완료!",5);
            return 1; // 제대로 퇴실
        }
        else {
            // 입장안했는데 퇴실 잘못누른경우
            return 0;
        }
    }

    public List<StudentStudy> getMyStudy(Student student){
        List<StudentStudy> studentStudies = studentStudyRepository.findStudentStudiesByStudent(student);
        return studentStudies;
    }
    public long totalMyStudy(List<StudentStudy> studentStudies) {
        long minutes=0;
        for(StudentStudy studentStudy : studentStudies){ // 내 자습 for 문으로 돌림
            if (studentStudy.isOut()){
                // 퇴실 처리가 완료된 자습
                 minutes +=  studentStudy.getTotal();
            }        }
        return minutes;
    }





}
