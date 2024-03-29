package com.example.nf.newfine_backend.member.student.service;

import com.example.nf.newfine_backend.Homework.Repository.SHomeworkRepository;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.dto.*;
import com.example.nf.newfine_backend.member.student.exception.DuplicatedNicknameException;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final RedisTemplate redisTemplate;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;
    private final ListenerRepository listenerRepository;
    private final SHomeworkRepository sHomeworkRepository;

    @Transactional(readOnly = true)
    public StudentRankingDetailDto getMemberInfo(String nickname){
        return studentRepository.findByNickname(nickname)
                .map(StudentRankingDetailDto::of)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

//
    }


    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    // SecurityContext 는 전역
    @Transactional(readOnly = true)
    public StudentResponseDto getMyInfo() {
        // api 요청이 들어오면 필터에서 access token 복호화하여 유저 정보를 꺼낸 뒤  Security Context에 저장
        return studentRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(StudentResponseDto::of)
                .orElseThrow(() -> new CustomException(UNAUTHORIZED_MEMBER));
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public StudentResponseDto setNickname(NicknameRequestDto nicknameRequestDto){
        if (studentRepository.existsByNickname((nicknameRequestDto.getNickname()))) {
            throw new DuplicatedNicknameException("이미 사용 중인 닉네임입니다.");
        }

        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
//        Student student = studentRepository.findByPhoneNumber(nicknameRequestDto.getPhoneNumber()).orElseThrow(PhoneNumberNotFoundException::new);
        student.setNickname(nicknameRequestDto.getNickname());

        Student student1=studentRepository.save(student);

        System.out.println(student1.getNickname()+'\n'+ student1.getPoint());
        // Redis SortedSet 에 RedisKey, Score(SortedSet 내의 Key), Value 추가
        redisTemplate.opsForZSet().add("ranking", student1.getNickname(), student1.getPoint());

//        return StudentResponseDto.of(studentRepository.save(student));
        return StudentResponseDto.of(student1);
    }

    public StudentResponseDto updateNickname(NicknameRequestDto nicknameRequestDto){
        if (studentRepository.existsByNickname((nicknameRequestDto.getNickname()))) {
            throw new DuplicatedNicknameException("이미 사용 중인 닉네임입니다.");
        }

        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        redisTemplate.opsForZSet().remove("ranking",student.getNickname());
        student.setNickname(nicknameRequestDto.getNickname());
        Student student1=studentRepository.save(student);

        // Redis SortedSet 에 RedisKey, Score(SortedSet 내의 Key), Value 추가
        redisTemplate.opsForZSet().add("ranking", student1.getNickname(), student1.getPoint());

        return StudentResponseDto.of(student1);
    }

    @Transactional
    public String deleteStudent(DeleteRequestDto deleteRequestDto){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        if (!passwordEncoder.matches(deleteRequestDto.getPassword(), student.getPassword())) {
            throw new CustomException(INVALID_PASSWORD);
        }

        if (redisTemplate.opsForValue().get("RT:" + student.getId()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + student.getId());
        }
        redisTemplate.opsForZSet().remove("ranking", student.getNickname());
        // &*************************** 액토도.....?

        // 학생관련된 shomework 지우기
        List<Listener> listenerList= listenerRepository.findListenersByStudent(student);
        for (Listener listener : listenerList){
            if(sHomeworkRepository.findByListener(listener).isPresent()){
                sHomeworkRepository.deleteAllByListener(listener);
            }
        }
        studentRepository.delete(student);
        return "탈퇴 완료";
    }

    public String updatePassword(PasswordUpdateDto passwordUpdateDto){

        System.out.println(passwordUpdateDto.getPhoneNumber());

        Student student=studentRepository.findByPhoneNumber(passwordUpdateDto.getPhoneNumber()).orElseThrow(PhoneNumberNotFoundException::new);
        student.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));

        studentRepository.save(student);
        return "비번 변경 완료";
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

