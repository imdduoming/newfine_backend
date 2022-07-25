package com.example.nf.newfine_backend.student.controller;

import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.dto.DeleteRequestDto;
import com.example.nf.newfine_backend.student.dto.NicknameRequestDto;
import com.example.nf.newfine_backend.student.dto.StudentResponseDto;
import com.example.nf.newfine_backend.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import com.example.nf.newfine_backend.student.service.PointService;
import com.example.nf.newfine_backend.student.service.StudentService;
import com.example.nf.newfine_backend.student.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class StudentController {
    private final StudentService studentService;
    public final StudentRepository studentRepository;
    public final PointService pointService;

    @GetMapping("/me")
    public ResponseEntity<StudentResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(studentService.getMyInfo());
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<StudentResponseDto> getMemberInfo(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(studentService.getMemberInfo(phoneNumber));
    }

    //    /////// ㅁㄱ
//    @PostMapping("/myInfo")
//    public ResponseEntity<MemberResponseDto> getMyInfo(@RequestHeader("token") String token){
//        token.replace("Bearer ", "");
//        return ResponseEntity.ok(authService.findMemberByToken(token));
//    }
//
//
    @PostMapping("/nickname")
    public ResponseEntity<StudentResponseDto> setNickname(@RequestBody NicknameRequestDto nicknameRequestDto){
        return ResponseEntity.ok(studentService.setNickname(nicknameRequestDto));
    }

    @PostMapping(value = "/nickname/update")
    public ResponseEntity<StudentResponseDto> updateNickname(@RequestBody NicknameRequestDto nicknameRequestDto) {
        return ResponseEntity.ok(studentService.updateNickname(nicknameRequestDto));
//        return responseService.getSuccessResult();
    }

    //    @PostMapping("/myInfo")
//    public ResponseEntity<MemberResponseDto> getMyInfo(@RequestHeader("authorization") String token){
//        token.replace("Bearer ", "");
//        return ResponseEntity.ok(authService.findMemberByToken(token));
//    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteStudent(@RequestBody DeleteRequestDto deleteRequestDto) {
        return ResponseEntity.ok(studentService.deleteStudent(deleteRequestDto));
    }

    @PostMapping("/point")
    public String point(){
        //*******************************************************8
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        pointService.create(student,"포인트 클릭!!!!",5);

        return "성공인걸까아닌걸까^ㅠ^";
    }
}
