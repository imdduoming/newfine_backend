package com.example.nf.newfine_backend.member.controller;

import com.example.nf.newfine_backend.branch.domain.BranchStudent;
import com.example.nf.newfine_backend.branch.repository.BranchRepository;
import com.example.nf.newfine_backend.branch.repository.BranchStudentRepository;
import com.example.nf.newfine_backend.member.dto.*;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.service.AuthService;
import com.example.nf.newfine_backend.member.student.dto.PasswordUpdateDto;
import com.example.nf.newfine_backend.member.student.dto.PhoneNumberDto;
import com.example.nf.newfine_backend.member.student.dto.StudentResponseDto;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.student.service.MessageService;
import com.example.nf.newfine_backend.member.student.service.StudentService;
import com.example.nf.newfine_backend.member.teacher.dto.TeacherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.DUPLICATE_MEMBER;
import static com.example.nf.newfine_backend.member.exception.ErrorCode.MEMBER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")    // SecurityConfig 에서 요청 허용, 토큰 검증 로직 필요 X
public class AuthController {
    private final AuthService authService;
    private final MessageService messageService;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final BranchStudentRepository branchStudentRepository;
    private final BranchRepository branchRepository;

    @PostMapping("/signup")
    public ResponseEntity<StudentResponseDto> signup(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.signup(signUpDto));    // 상태 코드 + data(body) (ResponseEntity 에 헤더 정보, 상태 코드 담을 수 있음)
    }

    @PostMapping("/signuptest")
    public ResponseEntity<StudentResponseDto> signuptest(@RequestBody SignUpDto signUpDto) {
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

        System.out.println("되니..?");
        System.out.println(tokenRequestDto.getAccessToken());
        System.out.println(tokenRequestDto.getRefreshToken());

//        TokenRequestDto tokenRequestDto=TokenRequestDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(token)
//                .build();
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    // 전화번호 인증번호 전송
    @PostMapping("/sendSignUpMessage")
    public ResponseEntity<String> sendSignUpMessage(@RequestBody SignUpAuthDto signUpAuthDto) {
//        int randomNumber=(int)((Math.random()* (9999 - 1000 + 1)) + 1000);
        int randomNumber= 0000;

        //************************* 추후 DB 전화번호와 일치하는지 확인해야 함 ->일단 했음. ^^
        BranchStudent bs=branchStudentRepository.findByPhoneNumber(signUpAuthDto.getPhoneNumber()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        System.out.println(String.valueOf(bs.getBranch().getId()));
        System.out.println(signUpAuthDto.getBranch());
        if (!Objects.equals(String.valueOf(bs.getBranch().getId()), signUpAuthDto.getBranch())){
//            throw new RuntimeException("이 분원엔 그런 학생이 없다!!");
            throw new CustomException(MEMBER_NOT_FOUND);
        }

        // 전화번호 중복 확인
        if (studentRepository.existsByPhoneNumber(signUpAuthDto.getPhoneNumber())) {
            throw new CustomException(DUPLICATE_MEMBER);
        }

//        messageService.sendMessage(phoneNumberDto, String.valueOf(randomNumber));
//        return String.valueOf(randomNumber);
        return ResponseEntity.ok(messageService.sendMessage(signUpAuthDto.getPhoneNumber(), String.valueOf(randomNumber)));
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody PhoneNumberDto phoneNumberDto) {
//        int randomNumber=(int)((Math.random()* (9999 - 1000 + 1)) + 1000);
        int randomNumber = 0000;

        // 가입된 회원인지 확인
        if (!studentRepository.existsByPhoneNumber(phoneNumberDto.getPhoneNumber())) {
            throw new CustomException(MEMBER_NOT_FOUND);
        }
        return ResponseEntity.ok(messageService.sendMessage(phoneNumberDto.getPhoneNumber(), String.valueOf(randomNumber)));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenRequestDto tokenRequestDto) {
        // validation check
//        if (errors.hasErrors()) {
//            throw new RuntimeException();
//        }
        return ResponseEntity.ok(authService.logout(tokenRequestDto));
    }

    @PostMapping("/refreshTokenWeb")
    public TokenDto refreshToken(@RequestBody TokenRequestDto tokenRequestDto) {

        System.out.println("되니..?");
        System.out.println(tokenRequestDto.getAccessToken());
        System.out.println(tokenRequestDto.getRefreshToken());

        return authService.reissue(tokenRequestDto);
    }

//    @PostMapping("/loginCheck")
//    public String loginCheck(HttpServletRequest request) {
//
//        return authService.loginCheck();
//    }

    @ResponseBody
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public ResponseEntity<TokenDto> login(HttpServletRequest request, SignInDto signInDto) {

        signInDto.setPhoneNumber(request.getParameter("phoneNumber"));
        signInDto.setPassword(request.getParameter("password"));
        return ResponseEntity.ok(authService.login(signInDto));
    }

    @PostMapping("/newPassword")
    public ResponseEntity updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDto){

        return ResponseEntity.ok(studentService.updatePassword(passwordUpdateDto));
    }
}
