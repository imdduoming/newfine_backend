package com.example.nf.newfine_backend.student.service;

import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.dto.StudentResponseDto;
import com.example.nf.newfine_backend.student.exception.CustomException;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import com.example.nf.newfine_backend.student.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.nf.newfine_backend.student.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.nf.newfine_backend.student.exception.ErrorCode.UNAUTHORIZED_MEMBER;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public StudentResponseDto getMemberInfo(String phoneNumber){
        return studentRepository.findByPhoneNumber(phoneNumber)
                .map(StudentResponseDto::of)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
//                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    // SecurityContext는 전역
    @Transactional(readOnly = true)
    public StudentResponseDto getMyInfo() {
        // api 요청이 들어오면 필터에서 access token 복호화하여 유저 정보를 꺼낸 뒤  Security Context에 저장
        return studentRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(StudentResponseDto::of)
                .orElseThrow(() -> new CustomException(UNAUTHORIZED_MEMBER));
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    // SecurityContext는 전역 , Student 로 반환받기
    @Transactional(readOnly = true)
    public Student getUser() {
        // api 요청이 들어오면 필터에서 access token 복호화하여 유저 정보를 꺼낸 뒤  Security Context에 저장
        return studentRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new CustomException(UNAUTHORIZED_MEMBER));
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


}
