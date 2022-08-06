package com.example.nf.newfine_backend.member.student.repository;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.member.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByPhoneNumber(String phoneNumber); // 전화번호가 아이디
    boolean existsByPhoneNumber(String phoneNumber);    // 중복 가입 방지용

    Optional<Student> findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    List<Student> findStudentsByListeners(Course course);


//    Optional<Student> findByListener(Listener listener);
}
