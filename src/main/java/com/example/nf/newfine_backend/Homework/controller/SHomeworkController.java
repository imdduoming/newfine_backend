package com.example.nf.newfine_backend.Homework.controller;

import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.Homework.service.SHomeworkService;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SHomeworkController {

    private final SHomeworkService sHomeworkService;
    private final ListenerRepository listenerRepository;
    private final StudentRepository studentRepository;

    //댓글 작성
    /*
    @PostMapping("/sh/{thId}")

    public SHomeworkDto uploadSHomework(@PathVariable("thId") Long thId, @RequestBody SHomeworkDto sHomeworkDto) {
        Listener listener = listenerRepository.findById(1L).get();
        return sHomeworkService.createSHomework(thId, sHomeworkDto, listener);
    }
    */

    //댓글 목록 조회
    @GetMapping("/sh/list/{thId}")
    public List<SHomeworkDto> getSHomeworks(@PathVariable("thId") Long thId){
        return sHomeworkService.getSHomeworks(thId);
    }

    // shomework listener 별로 조회
    @GetMapping("/shlist")
    public List<SHomeworkDto> getSHomeworks2(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return sHomeworkService.getSHomeworks2(student);
    }

    //댓글 삭제
   /*
    @DeleteMapping("/sh/{thId}/{shId}")
    public String deleteSHomework(@PathVariable("thId") Long thId, @PathVariable("shId") Long shId) {
        // 추후 JWT 로그인 기능을 추가하고나서, 세션에 로그인된 유저와 댓글 작성자를 비교해서, 맞으면 삭제 진행하고
        // 틀리다면 예외처리를 해주면 된다.

        return sHomeworkService.deleteSHomework(shId);
    }
    */
}
