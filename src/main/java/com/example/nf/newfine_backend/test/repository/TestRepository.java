package com.example.nf.newfine_backend.test.repository;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByTestCode(String testCode);


    List<Test> findTestsByCourse(Course course);
}
