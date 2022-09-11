package com.example.nf.newfine_backend.admin.service;

import com.example.nf.newfine_backend.admin.dto.DeleteRequestByAdminDto;
import com.example.nf.newfine_backend.admin.dto.SignUpByAdminDto;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.dto.StudentResponseDto;
import com.example.nf.newfine_backend.member.student.exception.DuplicatedNicknameException;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.DUPLICATE_MEMBER;
import static com.example.nf.newfine_backend.member.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    // ****************** 관리자 페이지에서 학생 회원가입
    @Transactional
    public StudentResponseDto signupByAdmin(SignUpByAdminDto signUpByAdminDto) {
        if (studentRepository.existsByPhoneNumber(signUpByAdminDto.getPhoneNumber())) {
            throw new CustomException(DUPLICATE_MEMBER);
        }

        if (studentRepository.existsByNickname((signUpByAdminDto.getNickname()))) {
            throw new DuplicatedNicknameException("이미 사용 중인 닉네임입니다.");
        }

        Student student = signUpByAdminDto.toMember(passwordEncoder);
        student.setPoint(0);

        Student student1=studentRepository.save(student);

        System.out.println(student1.getNickname()+'\n'+ student1.getPoint());
        // Redis SortedSet 에 RedisKey, Score(SortedSet 내의 Key), Value 추가
        redisTemplate.opsForZSet().add("ranking", student1.getNickname(), student1.getPoint());

//        return "회원 가입 완료";
        return StudentResponseDto.of(studentRepository.save(student));
    }

    // ****************** 관리자 페이지에서 학생 탈퇴
    public String deleteStudentByAdmin(DeleteRequestByAdminDto deleteRequestByAdminDto){

//        if(studentRepository.findByPhoneNumber(deleteRequestByAdminDto.getPhoneNumber())==null){
//            throw new CustomException(MEMBER_NOT_FOUND);
//        }
        Student student=studentRepository.findByPhoneNumber(deleteRequestByAdminDto.getPhoneNumber()).orElseThrow(()->new PhoneNumberNotFoundException("회원 정보가 없습니다.\n회원가입을 먼저 해주세요."));
        if (!passwordEncoder.matches(deleteRequestByAdminDto.getPassword(), student.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        if (redisTemplate.opsForValue().get("RT:" + student.getId()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + student.getId());
        }
        redisTemplate.opsForZSet().remove("ranking", student.getNickname());
        // &*************************** 액토도.....?

        studentRepository.delete(student);

        return "탈퇴 완료";
    }
}
