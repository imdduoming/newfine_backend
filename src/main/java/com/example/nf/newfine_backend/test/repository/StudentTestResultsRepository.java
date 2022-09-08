package com.example.nf.newfine_backend.test.repository;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.study.StudentStudy;
import com.example.nf.newfine_backend.study.Study;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.example.nf.newfine_backend.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTestResultsRepository extends JpaRepository<StudentTestResults, Long> {
    Optional<StudentTestResults> findByTestAndStudentCode(Test test, String student_code);

    // 내 총점보다 높은 애들 가져오기
    List<StudentTestResults> findAllByTestAndTotalScoreAfter(Test test , int total_score);

    List<StudentTestResults> findAllByTestOrderByTotalScoreDesc(Test test);
    // 같은 시험본애들
    List<StudentTestResults> findAllByTest(Test test);

    Optional<StudentTestResults> findFirstByTestOrderByTotalScoreDesc(Test test);
    Optional<StudentTestResults> findFirstByTestOrderByTotalScoreAsc(Test test);
}
