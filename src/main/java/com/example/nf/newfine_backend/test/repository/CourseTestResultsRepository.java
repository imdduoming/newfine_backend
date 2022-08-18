package com.example.nf.newfine_backend.test.repository;

import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTestResultsRepository extends JpaRepository<CourseTestResults, Long> {
    List<CourseTestResults> findTop5ByTestOrderByCorrectAnsRate(Test test); // 역순
    List<CourseTestResults> findAllByTest(Test test);
    List<CourseTestResults> findAllByType(String type);
}
