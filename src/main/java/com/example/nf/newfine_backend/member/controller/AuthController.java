package com.example.nf.newfine_backend.member.controller;

import com.example.nf.newfine_backend.member.dto.SignInDto;
import com.example.nf.newfine_backend.member.dto.SignUpDto;
import com.example.nf.newfine_backend.member.dto.TokenDto;
import com.example.nf.newfine_backend.member.dto.TokenRequestDto;
import com.example.nf.newfine_backend.member.student.dto.*;
import com.example.nf.newfine_backend.member.teacher.dto.TeacherResponseDto;
import com.example.nf.newfine_backend.member.student.dto.*;
import com.example.nf.newfine_backend.member.service.AuthService;
import com.example.nf.newfine_backend.member.student.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")    // SecurityConfig 에서 요청 허용, 토큰 검증 로직 필요 X
public class AuthController {
    private final AuthService authService;
    private final MessageService messageService;

    @PostMapping("/signup")
    public ResponseEntity<StudentResponseDto> signup(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.signup(signUpDto));    // 상태 코드 + data(body) (ResponseEntity 에 헤더 정보, 상태 코드 담을 수 있음)
    }

    @PostMapping("/signupTeacher")
    public ResponseEntity<TeacherResponseDto> signupTeacher(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.signupTeacher(signUpDto));    // 상태 코드 + data(body) (ResponseEntity 에 헤더 정보, 상태 코드 담을 수 있음)
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(authService.login(signInDto));
    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<TokenDto> reissue(@RequestHeader("Authorization") String token, @RequestBody String accessToken) {
//        token.replace("Bearer ", "");
//
//        TokenRequestDto tokenRequestDto=TokenRequestDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(token)
//                .build();
//        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
//    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        token.replace("Bearer ", "");

//        TokenRequestDto tokenRequestDto=TokenRequestDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(token)
//                .build();
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    // 전화번호 인증번호 전송
    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody PhoneNumberDto phoneNumberDto) {
        int randomNumber=(int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

        //************************* 추후 DB 전화번호와 일치하는지 확인해야 함

//        messageService.sendMessage(phoneNumberDto, String.valueOf(randomNumber));
//        return String.valueOf(randomNumber);
        return ResponseEntity.ok(messageService.sendMessage(phoneNumberDto, String.valueOf(randomNumber)));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenRequestDto tokenRequestDto) {
        // validation check
//        if (errors.hasErrors()) {
//            throw new RuntimeException();
//        }
        return ResponseEntity.ok(authService.logout(tokenRequestDto));
    }
}
