package com.example.nf.newfine_backend.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);    // 중복 가입 방지용
}
