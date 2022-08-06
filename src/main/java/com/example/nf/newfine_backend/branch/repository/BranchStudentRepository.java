package com.example.nf.newfine_backend.branch.repository;

import com.example.nf.newfine_backend.branch.domain.BranchStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchStudentRepository extends JpaRepository<BranchStudent, Long> {
    Optional<BranchStudent> findByPhoneNumber(String PhoneNumber);
    Optional<BranchStudent> findByParentPhoneNumber(String ParentNumber);
}
