package com.example.nf.newfine_backend.test.repository;

import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTestResultsRepository extends JpaRepository<StudentTestResults, Long> {
}
