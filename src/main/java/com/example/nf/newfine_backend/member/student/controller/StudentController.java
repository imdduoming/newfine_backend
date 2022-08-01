package com.example.nf.newfine_backend.member.student.controller;

import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.dto.*;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.service.PointService;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.MessageService;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.DUPLICATE_MEMBER;
import static com.example.nf.newfine_backend.member.exception.ErrorCode.MEMBER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class StudentController {
    private final StudentService studentService;
    public final StudentRepository studentRepository;
    public final PointService pointService;
    public final MessageService messageService;

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

    @PostMapping("/newPassword")
    public ResponseEntity updatePassword(PasswordUpdateDto passwordUpdateDto){

        return ResponseEntity.ok(studentService.updatePassword(passwordUpdateDto));
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody PhoneNumberDto phoneNumberDto) {
        int randomNumber=(int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

        // 가입된 회원인지 확인
        if (!studentRepository.existsByPhoneNumber(phoneNumberDto.getPhoneNumber())) {
            throw new CustomException(MEMBER_NOT_FOUND);
        }
        return ResponseEntity.ok(messageService.sendMessage(phoneNumberDto, String.valueOf(randomNumber)));
    }

    @PostMapping("/point")
    public String point(){
        //*******************************************************8
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        pointService.create(student,"포인트 클릭!!!!",5);

        if(student.availableLevelUp()){
            student.levelUp();
        }

        return "성공인걸까아닌걸까^ㅠ^";
    }
}
