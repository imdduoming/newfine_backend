package com.example.nf.newfine_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepostiory extends JpaRepository<Student,Long> {
    //임시로 휴대폰번호로 학생을 찾아 출석에 저장하고자함
    Student findBySphoneNumber(String phoneNumber);
}
