package com.example.nf.newfine_backend.member.service;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.teacher.domain.Teacher;
import com.example.nf.newfine_backend.member.teacher.repository.TeacherRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserDetails 와 Authentication 의 패스워드를 비교하고 검증하는 로직을 처리

        if (teacherRepository.existsByPhoneNumber(username)){
            return teacherRepository.findByPhoneNumber(username)
                    .map(this::createTeacherDetails)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
        }
        return studentRepository.findByPhoneNumber(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴 -> 해당 UserDetails 객체를 SecurityContext 에 저장
    public UserDetails createUserDetails(Student student) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(student.getAuthority().toString());  // 권한 설정

        return new User(
                String.valueOf(student.getId()),
                student.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    private UserDetails createTeacherDetails(Teacher teacher) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(teacher.getTAuthority().toString());  // 권한 설정

        return new User(
                String.valueOf(teacher.getTId()),
                teacher.getTPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
